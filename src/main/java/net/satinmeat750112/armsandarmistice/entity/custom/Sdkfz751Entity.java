package net.satinmeat750112.armsandarmistice.entity.custom;


import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.satinmeat750112.armsandarmistice.ArmsAndArmistice;
import net.satinmeat750112.armsandarmistice.entity.constants.TankConstants;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class Sdkfz751Entity extends Animal implements GeoEntity{
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Float> CLIENT_STEER = SynchedEntityData.defineId(Sdkfz751Entity.class, EntityDataSerializers.FLOAT);
    private int currentGear = 0;
    private int CurrentAccelerationTicks = 0;
    private final int MinimumPassengers = 3;
    private final static double maxHealth = 100.0D;
    private final static double movementSpeed = 0.35D;
    private final static double knockBackResistance = 1.0D;
    private final float gunReach = 20f;
    private static final float MainGunDamage = 5.0f;
    protected static final RawAnimation FORWARD_ANIM = RawAnimation.begin().thenLoop("animation.sdkfz251.forward_normal");
    protected static final RawAnimation BACKWARD_ANIM = RawAnimation.begin().thenLoop("animation.sdkfz251.backward_normal");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.sdkfz251.idle_off");
    private Player controllingPassenger;

    public Sdkfz751Entity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, maxHealth)
                .add(Attributes.MOVEMENT_SPEED, movementSpeed)
                .add(Attributes.KNOCKBACK_RESISTANCE, knockBackResistance)
                .add(Attributes.ATTACK_DAMAGE, MainGunDamage)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CLIENT_STEER, 0.0F);
    }

    public float getClientSteer() {
        return this.entityData.get(CLIENT_STEER);
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (!this.level().isClientSide) {
            if (player.isShiftKeyDown()) {
                return InteractionResult.PASS;
            }
            player.startRiding(this);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }


    @Override
    public void travel(@NotNull Vec3 travelVector) {
        controllingPassenger = (Player) this.getControllingPassenger();
        if (this.isAlive()) {
            if (this.controllingPassenger == null) {
                ArmsAndArmistice.LOGGER.warn("Sdkfz751Entity is being controlled by a null passenger.");
                return;
            }



            float steeringInput = controllingPassenger.xxa;
            float forwardInput = controllingPassenger.zza;

            if (!this.level().isClientSide) {
                this.entityData.set(CLIENT_STEER, steeringInput);
            }

                if (forwardInput < -TankConstants.INPUT_MINIMUM) {
                    this.setYRot(this.getYRot() + (steeringInput * TankConstants.STEERING_SENSITIVE));
                } else {
                    this.setYRot(this.getYRot() - (steeringInput * TankConstants.STEERING_SENSITIVE));
                }

            this.yRotO = this.getYRot();
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.yBodyRot;
            this.setXRot(0.0F);
            this.setRot(this.getYRot(), this.getXRot());

                double targetSpeed = 0.0;

                if (forwardInput > TankConstants.INPUT_MINIMUM) {
                    this.CurrentAccelerationTicks++;
                    if (this.CurrentAccelerationTicks < TankConstants.ACCELERATIONTICKS_MINIMUM) {
                        this.currentGear = 1;
                        targetSpeed = 0.1D;
                    } else if (this.CurrentAccelerationTicks < TankConstants.ACCELERATIONTICKS_MAXIMUM) {
                        this.currentGear = 2;
                        targetSpeed = 0.6D;
                    } else {
                        this.currentGear = 3;
                        targetSpeed = 0.8D;
                    }
                } else if (forwardInput < -TankConstants.INPUT_MINIMUM) {
                    this.currentGear = TankConstants.MIN_GEAR;
                    this.CurrentAccelerationTicks = 0;
                    targetSpeed = -0.2D;
                } else {
                    this.currentGear = 0;
                    if (Math.abs(steeringInput) > TankConstants.INPUT_MINIMUM) {
                        this.CurrentAccelerationTicks = Math.max(1, this.CurrentAccelerationTicks - 1);
                    } else {
                        this.CurrentAccelerationTicks = 0;
                    }
                }

                double smoothSpeed = getSmoothSpeed(targetSpeed);

                float yawRadians = (float) Math.toRadians(this.getYRot());
                double motionX = -Math.sin(yawRadians) * smoothSpeed;
                double motionZ = Math.cos(yawRadians) * smoothSpeed;

            // Directly overwrite the vector array map to override all engine drag logic bounds
            this.setDeltaMovement(motionX, this.getDeltaMovement().y, motionZ);
            this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
            return;
        }

            if (!this.level().isClientSide) {
                this.entityData.set(CLIENT_STEER, 0.0F);
            }
            this.currentGear = 0;
            this.CurrentAccelerationTicks = 0;

        double currentSpeed = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
        double smoothSpeed = currentSpeed * 0.85D;
        if (smoothSpeed < 0.01D)
        {
            float yawRadians = (float) Math.toRadians(this.getYRot());
            this.setDeltaMovement(-Math.sin(yawRadians) * smoothSpeed, this.getDeltaMovement().y, Math.cos(yawRadians) * smoothSpeed);
            this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
            return;
        }

        super.travel(travelVector);
    }

    private double getSmoothSpeed(double targetSpeed) {
        double currentSpeed = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
        double yawRadCheck = Math.toRadians(this.getYRot());
        if ((this.getDeltaMovement().x * -Math.sin(yawRadCheck) + this.getDeltaMovement().z * Math.cos(yawRadCheck)) < 0) {
            currentSpeed = -currentSpeed;
        }

        double smoothSpeed = currentSpeed;


        if (currentSpeed < targetSpeed) {
            smoothSpeed = Math.min(targetSpeed, currentSpeed + TankConstants.ACCELERATION_RATE);
        } else if (currentSpeed > targetSpeed) {
            smoothSpeed = Math.max(targetSpeed, currentSpeed - TankConstants.DECELERATION_RATE);
        }
        return smoothSpeed;
    }

    @Override
    protected void positionRider(net.minecraft.world.entity.@NotNull Entity rider, net.minecraft.world.entity.Entity.@NotNull MoveFunction moveFunction) {
        if (this.hasPassenger(rider)) {

            int seatIndex = this.getPassengers().indexOf(rider);

            double offsetX = 0.0;
            double offsetY = 20.15 / 16.0;
            double offsetZ = 0.0;

            if (seatIndex == 0) {
                offsetX = 0.0 / 16.0;
                offsetZ = -3.95 / 16.0;
            } else if (seatIndex == 1) {
                offsetX = 12.0 / 16.0;
                offsetZ = -3.95 / 16.0;
            } else if (seatIndex == 2) {
                offsetX = 0.0 / 16.0;
                offsetZ = -22.7 / 16.0;
            } else if (seatIndex == 3) {
                offsetX = 12.0 / 16.0;
                offsetZ = -22.7 / 16.0;
            } else if (seatIndex == 4) {
                offsetX = 12.0 / 16.0;
                offsetZ = -41.45 / 16.0;
            } else if (seatIndex == 5) {
                offsetX = 0.0 / 16.0;
                offsetZ = -41.45 / 16.0;
            }

            double yawRadians = Math.toRadians(this.getYRot());
            double xPosition = this.getX() + (offsetX * Math.cos(yawRadians) - offsetZ * Math.sin(yawRadians));
            double zPosition = this.getZ() + (offsetX * Math.sin(yawRadians) + offsetZ * Math.cos(yawRadians));
            double yPosition = this.getY() + offsetY + rider.getMyRidingOffset();

            moveFunction.accept(rider, xPosition, yPosition, zPosition);
        }
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof Player player ? player : null;
    }

    @Override
    protected boolean canAddPassenger(net.minecraft.world.entity.@NotNull Entity passenger) {
        return this.getPassengers().size() < MinimumPassengers;
    }



    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob ageableMob) {
        return null;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "drive_controller", 5, event -> {
            if (this.getControllingPassenger() instanceof Player passenger) {
                if (this.currentGear > 0) {
                    return event.setAndContinue(FORWARD_ANIM);
                } else if (this.currentGear < 0) {
                    return event.setAndContinue(BACKWARD_ANIM);
                }

                if (Math.abs(passenger.xxa) > TankConstants.INPUT_MINIMUM) {
                    return event.setAndContinue(FORWARD_ANIM);
                }
            }
            return event.setAndContinue(IDLE_ANIM);
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
    public boolean doHurtTarget(@NotNull Entity target) {
        //play sounds etc. anything before attack

        BlockHitResult result = Projectile.getTargetOfGun(this.level(), this.controllingPassenger, gunReach);
        ArmsAndArmistice.LOGGER.warn("Hit Result: {}, Block Position: {}", result.getType(), result.getBlockPos());
        boolean attackResult = super.doHurtTarget(target);

        //after attack
        return attackResult;
    }
}


