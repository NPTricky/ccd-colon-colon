package artemis.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class Utils {

    public static float cubicInterpolation(final float v0, final float v1,
            final float v2, final float v3, final float t) {
        final float t2 = t * t;
        final float a0 = v3 - v2 - v0 + v1;
        final float a1 = v0 - v1 - a0;
        final float a2 = v2 - v0;
        final float a3 = v1;

        return (a0 * (t * t2)) + (a1 * t2) + (a2 * t) + a3;
    }

    public static float quadraticBezierInterpolation(final float a,
            final float b, final float c, final float t) {
        return (((1f - t) * (1f - t)) * a) + (((2f * t) * (1f - t)) * b)
                + ((t * t) * c);
    }

    public static float lengthOfQuadraticBezierCurve(final float x0,
            final float y0, final float x1, final float y1, final float x2,
            final float y2) {
        if ((x0 == x1 && y0 == y1) || (x1 == x2 && y1 == y2)) {
            return distance(x0, y0, x2, y2);
        }

        float ax, ay, bx, by;
        ax = x0 - 2 * x1 + x2;
        ay = y0 - 2 * y1 + y2;
        bx = 2 * x1 - 2 * x0;
        by = 2 * y1 - 2 * y0;
        final float A = 4 * (ax * ax + ay * ay);
        final float B = 4 * (ax * bx + ay * by);
        final float C = bx * bx + by * by;

        final float Sabc = 2f * (float) Math.sqrt(A + B + C);
        final float A_2 = (float) Math.sqrt(A);
        final float A_32 = 2f * A * A_2;
        final float C_2 = 2f * (float) Math.sqrt(C);
        final float BA = B / A_2;

        return (A_32 * Sabc + A_2 * B * (Sabc - C_2) + (4f * C * A - B * B)
                * (float) Math.log((2 * A_2 + BA + Sabc) / (BA + C_2)))
                / (4 * A_32);
    }

    public static float lerp(final float a, final float b, final float t) {
        if (t < 0) {
            return a;
        }
        return a + t * (b - a);
    }

    public static float distance(final float x1, final float y1,
            final float x2, final float y2) {
        return euclideanDistance(x1, y1, x2, y2);
    }

    public static boolean doCirclesCollide(final float x1, final float y1,
            final float radius1, final float x2, final float y2,
            final float radius2) {
        final float dx = x2 - x1;
        final float dy = y2 - y1;
        final float d = radius1 + radius2;
        return (dx * dx + dy * dy) < (d * d);
    }

    public static float euclideanDistanceSq2D(final float x1, final float y1,
            final float x2, final float y2) {
        final float dx = x1 - x2;
        final float dy = y1 - y2;
        return dx * dx + dy * dy;
    }

    public static float manhattanDistance(final float x1, final float y1,
            final float x2, final float y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static float euclideanDistance(final float x1, final float y1,
            final float x2, final float y2) {
        final float a = x1 - x2;
        final float b = y1 - y2;

        return (float) FastMath.sqrt(a * a + b * b);
    }

    public static float angleInDegrees(final float ownerRotation,
            final float x1, final float y1, final float x2, final float y2) {
        return Math.abs(ownerRotation - angleInDegrees(x1, y1, x2, y2)) % 360;
    }

    public static float angleInDegrees(final float originX,
            final float originY, final float targetX, final float targetY) {
        return (float) Math.toDegrees(Math.atan2(targetY - originY, targetX
                - originX));
    }

    public static float angleInRadians(final float originX,
            final float originY, final float targetX, final float targetY) {
        return (float) Math.atan2(targetY - originY, targetX - originX);
    }

    public static boolean shouldRotateCounterClockwise(final float angleFrom,
            final float angleTo) {
        final float diff = (angleFrom - angleTo) % 360;
        return diff > 0 ? diff < 180 : diff < -180;
    }

    public static float getRotatedX(final float currentX, final float currentY,
            final float pivotX, final float pivotY, final float angleDegrees) {
        final float x = currentX - pivotX;
        final float y = currentY - pivotY;
        final float xr = (x * TrigLUT.cosDeg(angleDegrees))
                - (y * TrigLUT.sinDeg(angleDegrees));
        return xr + pivotX;
    }

    public static float getRotatedY(final float currentX, final float currentY,
            final float pivotX, final float pivotY, final float angleDegrees) {
        final float x = currentX - pivotX;
        final float y = currentY - pivotY;
        final float yr = (x * TrigLUT.sinDeg(angleDegrees))
                + (y * TrigLUT.cosDeg(angleDegrees));
        return yr + pivotY;
    }

    public static float getXAtEndOfRotatedLineByOrigin(final float x,
            final float lineLength, final float angleDegrees) {
        return x + TrigLUT.cosDeg(angleDegrees) * lineLength;
    }

    public static float getYAtEndOfRotatedLineByOrigin(final float y,
            final float lineLength, final float angleDegrees) {
        return y + TrigLUT.sinDeg(angleDegrees) * lineLength;
    }

    public static boolean collides(final float x1, final float y1,
            final float radius1, final float x2, final float y2,
            final float radius2) {
        float d = Utils.distance(x1, y1, x2, y2);

        d -= radius1 + radius2;

        return d < 0;
    }

    public static String readFileContents(final String file) {
        final InputStream is = Utils.class.getClassLoader()
                .getResourceAsStream(file);
        String contents = "";
        try {
            if (is != null) {
                final Writer writer = new StringWriter();

                final char[] buffer = new char[1024];
                final Reader reader = new BufferedReader(new InputStreamReader(
                        is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }

                contents = writer.toString();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        return contents;
    }

}
