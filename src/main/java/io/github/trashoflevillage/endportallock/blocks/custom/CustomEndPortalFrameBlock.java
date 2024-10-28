package io.github.trashoflevillage.endportallock.blocks.custom;

import com.google.common.base.Predicates;
import io.github.trashoflevillage.endportallock.EndPortalLock;
import io.github.trashoflevillage.endportallock.blocks.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Optional;

public class CustomEndPortalFrameBlock extends Block {
    public static final DirectionProperty FACING;
    public static final BooleanProperty FILLED;
    protected static final VoxelShape FRAME_SHAPE;
    private static BlockPattern COMPLETED_FRAME;
    private final Identifier FILLER_ITEM_IDENTIFIER;

    public CustomEndPortalFrameBlock(AbstractBlock.Settings settings, int index) {
        super(settings);
        FILLER_ITEM_IDENTIFIER = Identifier.of(EndPortalLock.PORTAL_ITEMS.get(index));
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(FILLED, false));
    }

    protected boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return FRAME_SHAPE;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)((BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())).with(FILLED, false);
    }

    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return (Boolean)state.get(FILLED) ? 15 : 0;
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, FILLED});
    }

    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Optional<RegistryEntry.Reference<Item>> itemReference = Registries.ITEM.getEntry(FILLER_ITEM_IDENTIFIER);
        if (itemReference.isPresent()) {
            Item item = itemReference.get().value();

            if (stack.itemMatches(item.getRegistryEntry()) && !state.get(FILLED)) {
                if (world.isClient) {
                    return ItemActionResult.SUCCESS;
                } else {
                    BlockState state2 = (BlockState)state.with(CustomEndPortalFrameBlock.FILLED, true);
                    world.setBlockState(pos, state2, 2);
                    stack.decrementUnlessCreative(1, player);
                    player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                    world.syncWorldEvent(1503, pos, 0);
                    BlockPattern.Result result = CustomEndPortalFrameBlock.getCompletedFramePattern().searchAround(world, pos);
                    if (result != null) {
                        BlockPos pos2 = result.getFrontTopLeft().add(-3, 0, -3);

                        for(int i = 0; i < 3; ++i) {
                            for(int j = 0; j < 3; ++j) {
                                world.setBlockState(pos2.add(i, 0, j), Blocks.END_PORTAL.getDefaultState(), 2);
                            }
                        }

                        world.syncGlobalEvent(1038, pos2.add(1, 0, 1), 0);
                    }
                    
                    return ItemActionResult.CONSUME;
                }
            }
        }
        return ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
    }

    public static BlockPattern getCompletedFramePattern() {
        if (COMPLETED_FRAME == null) {
            COMPLETED_FRAME =
                    BlockPatternBuilder.start()
                            .aisle(new String[]{"?vvv?", ">???<", ">???<", ">???<", "?^^^?"})
                            .where('?', CachedBlockPosition.matchesBlockState(BlockStatePredicate.ANY))
                            .where('^', CachedBlockPosition.matchesBlockState(
                                    (blockState) -> {
                                        return isStateOfFilledCustomPortalFrame(blockState)
                                                && blockState.get(FACING) == Direction.SOUTH;
                                    }
                            ))
                            .where('>', CachedBlockPosition.matchesBlockState(
                                    (blockState) -> {
                                        return isStateOfFilledCustomPortalFrame(blockState)
                                                && blockState.get(FACING) == Direction.WEST;
                                    }
                            ))
                            .where('v', CachedBlockPosition.matchesBlockState(
                                    (blockState) -> {
                                        return isStateOfFilledCustomPortalFrame(blockState)
                                                && blockState.get(FACING) == Direction.NORTH;
                                    }
                            ))
                            .where('<', CachedBlockPosition.matchesBlockState(
                                    (blockState) -> {
                                        return isStateOfFilledCustomPortalFrame(blockState)
                                                && blockState.get(FACING) == Direction.EAST;
                                    }
                            ))
                            .build();
        }

        return COMPLETED_FRAME;
    }

    private static boolean isStateOfFilledCustomPortalFrame(BlockState state) {
        return (isStateOfCustomPortalFrame(state) && state.get(FILLED));
    }

    private static boolean isStateOfCustomPortalFrame(BlockState state) {
        return state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME1) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME2) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME3) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME4) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME5) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME6) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME7) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME8) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME9) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME10) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME11) ||
        state.isOf(ModBlocks.CUSTOM_PORTAL_FRAME12);
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        FILLED = BooleanProperty.of("filled");
        FRAME_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0);
    }
}
