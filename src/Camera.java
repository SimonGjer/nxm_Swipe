import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;


public class Camera {

	public static PerspectiveCamera camera;
	public static double distToLookAtIni = 15;
	public static double distToLookAt = 15;
	public static double x, y, z;
	public static double xLook, yLook, zLook;
	public static double xMove, yMove, zMove;

	public static double xRot, yRot, zRot;

	public static final double pi_180 = Math.PI / 180.0;

	public static PerspectiveCamera createCamera() {
		camera = new PerspectiveCamera(true); //ParallelCamera ?
		camera.getTransforms().addAll(new Rotate(0, Rotate.Y_AXIS), new Rotate(-90, Rotate.X_AXIS), new Translate(0, -0, -15));
		camera.setFarClip(1_000); //default 100
		return camera;
	}

	public static void rotateX(double dxRot) {
		xRot += dxRot;
		if (xRot < 0) xRot = 0;
		else if (xRot > 90) xRot = 90;
		update();
	}

	public static void distLookAt(double dDist) {
		distToLookAtIni += dDist;
		if (distToLookAtIni < 2) distToLookAtIni = 2;
		update();
	}

	public static void dMoveX(double dx) {
		xMove += dx; update();
	}
	public static void dMoveY(double dy) {
		zMove += dy; update();
	}
	public static void dMoveZ(double dz) {
		yMove += dz; if (yMove < -3) yMove = -3; update();
	}
	public static void dMoveXY(double dx, double dy) {
		xMove += dx; zMove += dy; update();
	}
	public static void resetMove() {
		xMove = 0; yMove = 0; zMove = 0; update();
	}
	
	public static void resetRot() {
		xRot = 0; yRot = 0; zRot = 0; update(); camera.setRotationAxis(Rotate.Y_AXIS); camera.setRotate(0);
	}

	public static void update() {
		if (camera == null) return;
		distToLookAt = distToLookAtIni * Super.getXYZMax() / 7;

		//		;;;System.out.println("Super.getXYZMax(): " + Super.getXYZMax());
		//		;;;System.out.println("distToLookAt: " + distToLookAt);

		z = zLook + distToLookAt * Math.sin(xRot * pi_180);
		y = yLook + distToLookAt * Math.cos(xRot * pi_180);

		//		x = xLook + distToLookAt * Math.sin(yRot * pi_180);
		//		z = z * Math.cos(yRot * pi_180);

		//		System.out.println("camera.getTransforms(): " + camera.getTransforms());
		//		System.out.println("x, y, z " + x + "," + y + "," + z);

		camera.getTransforms().clear();
		camera.getTransforms().addAll(new Translate(-x + xMove, -y - yMove - Super.getZMax() / 2.0, -z + zMove), new Rotate(xRot - 90, Rotate.X_AXIS), new Rotate(yRot, Rotate.Y_AXIS));
	}
}
