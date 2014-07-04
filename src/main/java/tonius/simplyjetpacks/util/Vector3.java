package tonius.simplyjetpacks.util;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

public class Vector3 implements Cloneable {

    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {
        this(0, 0, 0);
    }

    public Vector3(Vector3 vector) {
        this(vector.x, vector.y, vector.z);
    }

    public Vector3(double amount) {
        this(amount, amount, amount);
    }

    public Vector3(Entity par1) {
        this(par1.posX, par1.posY, par1.posZ);
    }

    public Vector3(TileEntity par1) {
        this(par1.xCoord, par1.yCoord, par1.zCoord);
    }

    public Vector3(Vec3 par1) {
        this(par1.xCoord, par1.yCoord, par1.zCoord);

    }

    public Vector3(MovingObjectPosition par1) {
        this(par1.blockX, par1.blockY, par1.blockZ);
    }

    public Vector3(ChunkCoordinates par1) {
        this(par1.posX, par1.posY, par1.posZ);
    }

    public Vector3(ForgeDirection direction) {
        this(direction.offsetX, direction.offsetY, direction.offsetZ);
    }

    public Vector3(float rotationYaw, float rotationPitch) {
        this(Math.cos(Math.toRadians(rotationYaw + 90)), Math.sin(Math.toRadians(-rotationPitch)), Math.sin(Math.toRadians(rotationYaw + 90)));
    }

    public int intX() {
        return (int) Math.floor(this.x);
    }

    public int intY() {
        return (int) Math.floor(this.y);
    }

    public int intZ() {
        return (int) Math.floor(this.z);
    }

    public float floatX() {
        return (float) this.x;
    }

    public float floatY() {
        return (float) this.y;
    }

    public float floatZ() {
        return (float) this.z;
    }

    @Override
    public Vector3 clone() {
        return new Vector3(this);
    }

    public Vec3 toVec3() {
        return Vec3.createVectorHelper(this.x, this.y, this.z);
    }

    public Vector3 translate(Vector3 par1) {
        this.x += par1.x;
        this.y += par1.y;
        this.z += par1.z;
        return this;
    }

    public Vector3 translate(double par1) {
        this.x += par1;
        this.y += par1;
        this.z += par1;
        return this;
    }

    public static Vector3 translate(Vector3 translate, Vector3 par1) {
        translate.x += par1.x;
        translate.y += par1.y;
        translate.z += par1.z;
        return translate;
    }

    public static Vector3 translate(Vector3 translate, double par1) {
        translate.x += par1;
        translate.y += par1;
        translate.z += par1;
        return translate;
    }

    public Vector3 scale(double amount) {
        this.x *= amount;
        this.y *= amount;
        this.z *= amount;
        return this;
    }

    public Vector3 scale(Vector3 amount) {
        this.x *= amount.x;
        this.y *= amount.y;
        this.z *= amount.z;
        return this;
    }

    public static Vector3 scale(Vector3 vec, double amount) {
        return vec.scale(amount);
    }

    public static Vector3 scale(Vector3 vec, Vector3 amount) {
        return vec.scale(amount);
    }

    public double getMagnitude() {
        return Math.sqrt(this.getMagnitudeSquared());
    }

    public double getMagnitudeSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public Vector3 normalize() {
        double d = this.getMagnitude();

        if (d != 0) {
            this.scale(1 / d);
        }

        return this;
    }

    public Vector3 rotate(float angle, Vector3 axis) {
        return translateMatrix(getRotationMatrix(angle, axis), this.clone());
    }

    public double[] getRotationMatrix(float angle) {
        double[] matrix = new double[16];
        Vector3 axis = this.clone().normalize();
        double x = axis.x;
        double y = axis.y;
        double z = axis.z;
        angle *= 0.0174532925D;
        float cos = (float) Math.cos(angle);
        float ocos = 1.0F - cos;
        float sin = (float) Math.sin(angle);
        matrix[0] = (x * x * ocos + cos);
        matrix[1] = (y * x * ocos + z * sin);
        matrix[2] = (x * z * ocos - y * sin);
        matrix[4] = (x * y * ocos - z * sin);
        matrix[5] = (y * y * ocos + cos);
        matrix[6] = (y * z * ocos + x * sin);
        matrix[8] = (x * z * ocos + y * sin);
        matrix[9] = (y * z * ocos - x * sin);
        matrix[10] = (z * z * ocos + cos);
        matrix[15] = 1.0F;
        return matrix;
    }

    public static Vector3 translateMatrix(double[] matrix, Vector3 translation) {
        double x = translation.x * matrix[0] + translation.y * matrix[1] + translation.z * matrix[2] + matrix[3];
        double y = translation.x * matrix[4] + translation.y * matrix[5] + translation.z * matrix[6] + matrix[7];
        double z = translation.x * matrix[8] + translation.y * matrix[9] + translation.z * matrix[10] + matrix[11];
        translation.x = x;
        translation.y = y;
        translation.z = z;
        return translation;
    }

    public static double[] getRotationMatrix(float angle, Vector3 axis) {
        return axis.getRotationMatrix(angle);
    }

    public void rotate(double yaw, double pitch, double roll) {
        double yawRadians = Math.toRadians(yaw);
        double pitchRadians = Math.toRadians(pitch);
        double rollRadians = Math.toRadians(roll);

        double x = this.x;
        double y = this.y;
        double z = this.z;

        this.x = x * Math.cos(yawRadians) * Math.cos(pitchRadians) + z * (Math.cos(yawRadians) * Math.sin(pitchRadians) * Math.sin(rollRadians) - Math.sin(yawRadians) * Math.cos(rollRadians)) + y * (Math.cos(yawRadians) * Math.sin(pitchRadians) * Math.cos(rollRadians) + Math.sin(yawRadians) * Math.sin(rollRadians));
        this.z = x * Math.sin(yawRadians) * Math.cos(pitchRadians) + z * (Math.sin(yawRadians) * Math.sin(pitchRadians) * Math.sin(rollRadians) + Math.cos(yawRadians) * Math.cos(rollRadians)) + y * (Math.sin(yawRadians) * Math.sin(pitchRadians) * Math.cos(rollRadians) - Math.cos(yawRadians) * Math.sin(rollRadians));
        this.y = -x * Math.sin(pitchRadians) + z * Math.cos(pitchRadians) * Math.sin(rollRadians) + y * Math.cos(pitchRadians) * Math.cos(rollRadians);
    }

    public void rotate(double yaw, double pitch) {
        this.rotate(yaw, pitch, 0);
    }

    public void rotate(double yaw) {
        double yawRadians = Math.toRadians(yaw);

        double x = this.x;
        double z = this.z;

        if (yaw != 0) {
            this.x = x * Math.cos(yawRadians) - z * Math.sin(yawRadians);
            this.z = x * Math.sin(yawRadians) + z * Math.cos(yawRadians);
        }
    }

    @Override
    public int hashCode() {
        return ("X:" + this.x + "Y:" + this.y + "Z:" + this.z).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector3) {
            Vector3 vector3 = (Vector3) o;
            return this.x == vector3.x && this.y == vector3.y && this.z == vector3.z;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Vector3 [" + this.x + "," + this.y + "," + this.z + "]";
    }

}
