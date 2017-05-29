package ai151.grassi.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ProgressBar;

public class CustomProgressBar {

    public static final String RED_BAR    = "red-bar";
    public static final String YELLOW_BAR = "yellow-bar";
    public static final String ORANGE_BAR = "orange-bar";
    public static final String GREEN_BAR  = "green-bar";
    public static final String[] barColorStyleClasses = { RED_BAR, ORANGE_BAR, YELLOW_BAR, GREEN_BAR };

    public static void setBarStyleClass(ProgressBar bar, String barStyleClass) {
        bar.getStyleClass().removeAll(barColorStyleClasses);
        bar.getStyleClass().add(barStyleClass);
    }

    public static void setInitialColor(ProgressBar bar) {
        if (bar.getProgress() < 0.3) {
            setBarStyleClass(bar, RED_BAR);
        } else if (bar.getProgress() < 0.5) {
            setBarStyleClass(bar, ORANGE_BAR);
        } else if (bar.getProgress() < 0.7) {
            setBarStyleClass(bar, YELLOW_BAR);
        } else if (bar.getProgress() < 1) {
            setBarStyleClass(bar, GREEN_BAR);
        }
    }
    public static void changeProgressBarColor(ProgressBar bar) {
        bar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double progress = newValue == null ? 0 : newValue.doubleValue();

                if (progress < 0.3) {
                    setBarStyleClass(bar, RED_BAR);
                } else if (progress < 0.5) {
                    setBarStyleClass(bar, ORANGE_BAR);
                } else if (progress < 0.7) {
                    setBarStyleClass(bar, YELLOW_BAR);
                }

                if(oldValue.doubleValue() < newValue.doubleValue()) {
                    if(progress > 0 && progress < 0.3) {
                        setBarStyleClass(bar, RED_BAR);
                    } else if (progress > 0.3 && progress < 0.5) {
                        setBarStyleClass(bar, ORANGE_BAR);
                    } else if (progress > 0.5 && progress < 0.7) {
                        setBarStyleClass(bar, YELLOW_BAR);
                    } else if (progress > 0.7) {
                        setBarStyleClass(bar, GREEN_BAR);
                    }
                }
            }
        });
    }

}
