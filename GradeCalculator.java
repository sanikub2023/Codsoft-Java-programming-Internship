import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GradeCalculator {

    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(mainPanel, BorderLayout.NORTH);

        
        JLabel subjectLabel = new JLabel("Number of Subjects:");
        JTextField subjectField = new JTextField(10);
        JButton enterButton = new JButton("Enter Marks");

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(subjectLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(subjectField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(enterButton, gbc);

    
        JPanel marksPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        JScrollPane marksScrollPane = new JScrollPane(marksPanel);
        marksScrollPane.setVisible(false);
        frame.add(marksScrollPane, BorderLayout.CENTER);

        
        JPanel resultPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel totalMarksLabel = new JLabel();
        JLabel avgPercentageLabel = new JLabel();
        JLabel gradeLabel = new JLabel();
        resultPanel.add(totalMarksLabel);
        resultPanel.add(avgPercentageLabel);
        resultPanel.add(gradeLabel);
        frame.add(resultPanel, BorderLayout.SOUTH);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numSubjects = Integer.parseInt(subjectField.getText());
                    if (numSubjects <= 0) {
                        JOptionPane.showMessageDialog(frame, "Enter a valid number of subjects.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    marksPanel.removeAll();
                    JTextField[] markFields = new JTextField[numSubjects];
                    for (int i = 0; i < numSubjects; i++) {
                        JLabel subjectMarkLabel = new JLabel("Marks for Subject " + (i + 1) + ":");
                        JTextField markField = new JTextField(10);
                        markFields[i] = markField;
                        marksPanel.add(subjectMarkLabel);
                        marksPanel.add(markField);
                    }
                    JButton calculateButton = new JButton("Calculate Grade");
                    calculateButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            double totalMarks = 0;
                            for (JTextField field : markFields) {
                                try {
                                    double marks = Double.parseDouble(field.getText());
                                    if (marks < 0 || marks > 100) {
                                        JOptionPane.showMessageDialog(frame, "Please enter valid marks (0-100).", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    totalMarks += marks;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(frame, "Enter valid numeric marks.", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            }
                            double averagePercentage = totalMarks / numSubjects;
                            String grade = calculateGrade(averagePercentage);

                            totalMarksLabel.setText("Total Marks: " + totalMarks);
                            avgPercentageLabel.setText("Average Percentage: " + averagePercentage + "%");
                            gradeLabel.setText("Grade: " + grade);
                        }
                    });
                    marksPanel.add(calculateButton);
                    marksScrollPane.setVisible(true);
                    frame.revalidate();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid number of subjects.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

     frame.setVisible(true);
    }
}
