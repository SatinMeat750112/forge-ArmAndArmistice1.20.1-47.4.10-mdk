package net.satinmeat750112.armsandarmistice.entity.Models;

import net.minecraft.core.Position;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class Projectile {

//    public void shoot(Level pLevel, Player pPlayer, float reach){
//
//    }
    public static BlockHitResult getTargetOfGun(Level pLevel, Player pPlayer, float reach) {
        // Get player's eye position
        Vec3 vec3 = pPlayer.getEyePosition();

        // Calculate rotation angles
        float f = pPlayer.getXRot();
        float f1 = pPlayer.getYRot();

        // Calculate direction vector based on pitch and yaw
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));

        // Combine to get final direction components
        float f6 = f3 * f4;
        float f7 = f2 * f4;

        // Calculate the end point of the ray
        Vec3 vec31 = vec3.add((double)f6 * reach, (double)f5 * reach, (double)f7 * reach);

        // Perform the clip/raycast
        return pLevel.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, pPlayer));
    }
}
