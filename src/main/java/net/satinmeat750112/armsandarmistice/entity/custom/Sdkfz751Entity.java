package net.satinmeat750112.armsandarmistice.entity.custom;





import net.minecraft.world.entity.*;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import software.bernie.geckolib.animatable.GeoEntity;


abstract class Sdkfz751Entity extends HalfTrack implements GeoEntity, ContainerEntity, HasCustomInventoryScreen, RiderShieldingMount

{
    @Override
    public boolean hasAttributes() {



        addChestVehicleSaveData();
        readChestVehicleSaveData();
    addPassenger();

        return super.hasAttributes();
    }

    protected abstract void addPassenger();

    private class addPassenger(Sdkfz751Entity) {
    }

    protected abstract void readChestVehicleSaveData();

    private void addChestVehicleSaveData() {
    }


}


