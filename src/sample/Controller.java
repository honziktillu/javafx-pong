package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;
    public GraphicsContext gc;
    public ArrayList<String> input;
    public ArrayList<Player> players;
    public Player player;
    public Player enemy;
    public Ball ball;
    public int p1Score = 0;
    public int p2Score = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = canvas.getGraphicsContext2D();
        input = new ArrayList<>();
        players = new ArrayList<>();
        player = new Player(30, 150, 20, canvas.getHeight() / 2 - 75, 8);
        enemy = new Player(30, 150, canvas.getWidth() - 50, canvas.getHeight() / 2 - 75, 8);
        players.add(player);
        players.add(enemy);
        enemy.setCanHit(true);
        enemy.setEnemy(true);
        ball = new Ball(25, 25, canvas.getWidth() / 2 - 12.5, canvas.getHeight() / 2 - 12.5, 5, 5);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
                player.aiMovement(ball, canvas.getHeight());
                enemy.aiMovement(ball, canvas.getHeight());
                handleCollision();
                checkBall();
                handleMovement();
            }
        };
        animationTimer.start();
    }

    private void draw() {
        gc.setFill(Paint.valueOf("BLACK"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Paint.valueOf("WHITE"));
        gc.fillRect(canvas.getWidth() / 2 - 1, 0, 2, canvas.getHeight());
        drawPlayer(player);
        drawPlayer(enemy);
        drawBall(ball);
        drawScore();
    }

    private void drawPlayer(Player player) {
        gc.setFill(Paint.valueOf("WHITE"));
        gc.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    private void drawBall(Ball ball) {
        gc.setFill(Paint.valueOf("WHITE"));
        gc.fillRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
    }

    private void drawScore() {
        gc.setFont(new Font(25));
        gc.fillText(String.valueOf(p1Score), canvas.getWidth() / 2 - 100, 50, 100);
        gc.fillText(String.valueOf(p2Score), canvas.getWidth() / 2 + 100, 50, 100);
    }

    private void newRound() {
        if (ball.getxSpeed() < 0) {
            ball.setxSpeed(-5);
            ball.setySpeed(-5);
            enemy.setCanHit(false);
            player.setCanHit(true);
        } else {
            ball.setxSpeed(5);
            ball.setySpeed(5);
            enemy.setCanHit(true);
            player.setCanHit(false);
        }
        ball.setX(canvas.getWidth() / 2 - ball.getWidth() / 2);
        ball.setY(canvas.getHeight() / 2 - ball.getHeight() / 2);
    }

    private void checkBall() {
        if (ball.getX() < 0) {
            newRound();
            p2Score++;
        } else if (ball.getX() + ball.getWidth() > canvas.getWidth()) {
            newRound();
            p1Score++;
        }
    }

    private void handleCollision() {
        for(Player p : players) {
            if (
                    ball.getX() < p.getX() + p.getWidth() &&
                    ball.getX() + ball.getWidth() > p.getX() &&
                    ball.getY() < p.getY() + p.getHeight() &&
                    ball.getY() + ball.getHeight() > p.getY() && p.isCanHit()
            ) {
                if (p.isEnemy()) {
                    player.setCanHit(true);
                    enemy.setCanHit(false);
                } else {
                    enemy.setCanHit(true);
                    player.setCanHit(false);
                }
                if (ball.getY() < p.getY() + p.getHeight() || ball.getY() + ball.getHeight() > p.getY()) {
                    ball.setySpeed(ball.getySpeed() + 1 * Math.signum(ball.getySpeed()));
                    ball.setxSpeed(ball.getxSpeed() + 1 * Math.signum(ball.getxSpeed()));
                }
                ball.setxSpeed(-ball.getxSpeed());
                ball.setxSpeed(ball.getxSpeed() * 1.01);
                ball.setySpeed(ball.getySpeed() * 1.01);
            }
        }

        ball.update(canvas.getHeight());
    }

    private void handleMovement() {
        if (input.contains("W")) {
            if (player.getY() > 0) {
                player.setY(player.getY() - player.getSpeed());
            }
        }
        if (input.contains("S")) {
            if (player.getY() + player.getHeight() < canvas.getHeight()) {
                player.setY(player.getY() + player.getSpeed());
            }
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (!input.contains(keyEvent.getCode().toString())) {
            input.add(keyEvent.getCode().toString());
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        input.remove(keyEvent.getCode().toString());
    }

}
