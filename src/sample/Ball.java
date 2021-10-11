package sample;

public class Ball {
    private double width;
    private double height;
    private double x;
    private double y;
    private double xSpeed;
    private double ySpeed;

    public Ball(double width, double height, double x, double y, double xSpeed, double ySpeed) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update(double canvasHeight) {
        if (y + height > canvasHeight || y < 0) {
            ySpeed = -ySpeed;
        }
        x += xSpeed;
        y += ySpeed;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }
}
