package io.github.trashoflevillage.endportallock.mixin;

import io.github.trashoflevillage.endportallock.blocks.ModBlocks;
import io.github.trashoflevillage.endportallock.blocks.custom.CustomEndPortalFrameBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.BuriedTreasureGenerator;
import net.minecraft.structure.StrongholdGenerator;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StrongholdGenerator.PortalRoom.class)
public abstract class PortalRoomMixin extends StructurePiece {
	protected PortalRoomMixin(StructurePieceType type, int length, BlockBox boundingBox) {
		super(type, length, boundingBox);
	}

	@Inject(method = "generate", at = @At(value = "TAIL", target = "Lnet/minecraft/structure/StrongholdGenerator$PortalRoom;generate(Lnet/minecraft/world/StructureWorldAccess;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/util/math/BlockBox;Lnet/minecraft/util/math/ChunkPos;Lnet/minecraft/util/math/BlockPos;)V"))
    private void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot, CallbackInfo ci) {
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME1.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.NORTH), 4, 3, 8, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME2.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.NORTH), 5, 3, 8, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME3.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.NORTH), 6, 3, 8, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME4.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.SOUTH), 4, 3, 12, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME5.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.SOUTH), 5, 3, 12, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME6.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.SOUTH), 6, 3, 12, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME7.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.EAST), 3, 3, 9, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME8.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.EAST), 3, 3, 10, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME9.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.EAST), 3, 3, 11, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME10.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.WEST), 7, 3, 9, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME11.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.WEST), 7, 3, 10, chunkBox);
		this.addBlock(world, ModBlocks.CUSTOM_PORTAL_FRAME12.getDefaultState().with(CustomEndPortalFrameBlock.FACING, Direction.WEST), 7, 3, 11, chunkBox);
	}
}