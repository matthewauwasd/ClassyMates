package ui;

import model.Classroom;

import javax.swing.*;
import java.awt.*;

public class ClassroomUI extends JFrame {

    private Classroom currentClassroom;
    private Component currentParent;

    public ClassroomUI(Classroom classroom, Component parent) {

        currentClassroom = classroom;
        currentParent = parent;
    }

}
