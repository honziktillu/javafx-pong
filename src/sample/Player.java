package sample;

public class Player {

    private double width;
    private double height;
    private double x;
    private double y;
    private double speed;
    private boolean canHit = false;
    private boolean isEnemy = false;

    public Player(double width, double height, double x, double y, double speed) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void aiMovement(Ball ball, double canvasHeight) {
        double middle = y + height / 2;
        if (middle > ball.getY()) {
            if (y > 0) {
                y -= speed;
            }
        } else {
            if (y + height < canvasHeight) {
                y += speed;
            }
        }
    }

    public boolean isCanHit() {
        return canHit;
    }

    public void setCanHit(boolean canHit) {
        this.canHit = canHit;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
