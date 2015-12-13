import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;


public class Camera {

	public static PerspectiveCamera camera;
	public static double distToLookAt = 15;
	public static double x, y, z;
	public static double xLook, yLook, zLook;
	
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
		distToLookAt += dDist;
		if (distToLookAt < 2) distToLookAt = 2;
		update();
	}
	
//	public static void rotateY(double dyRot) {
//		yRot += dyRot;
//		if (yRot < 0) yRot += 360;
//		else if (yRot > 360) yRot -= 360;
//		update();
//	}
	
	public static void update() {
		z = zLook + distToLookAt * Math.sin(xRot * pi_180);
		y = yLook + distToLookAt * Math.cos(xRot * pi_180);
		
//		x = xLook + distToLookAt * Math.sin(yRot * pi_180);
//		z = z * Math.cos(yRot * pi_180);
		
//		System.out.println("camera.getTransforms(): " + camera.getTransforms());
//		System.out.println("x, y, z " + x + "," + y + "," + z);

		camera.getTransforms().clear();
		camera.getTransforms().addAll(new Translate(-x, -y, -z), new Rotate(xRot - 90, Rotate.X_AXIS), new Rotate(yRot, Rotate.Y_AXIS));
	}
		
	
}
