package T01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextMergeProgram {

	private JFrame frame;
	private JTextField fileAPath;
	private JTextField fileBPath;
	private JTextField titleField;
	private JTextArea statusArea;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new TextMergeProgram().createAndShowGUI());
	}

	private void createAndShowGUI() {
		frame = new JFrame("텍스트 파일 병합기");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));

		panel.add(new JLabel("파일 A 경로:"));
		fileAPath = new JTextField();
		panel.add(fileAPath);

		panel.add(new JLabel("파일 B 경로:"));
		fileBPath = new JTextField();
		panel.add(fileBPath);

		panel.add(new JLabel("병합 파일 제목:"));
		titleField = new JTextField();
		panel.add(titleField);

		frame.add(panel, BorderLayout.NORTH);

		JButton mergeButton = new JButton("텍스트 병합하기");
		mergeButton.addActionListener(new MergeButtonActionListener());
		frame.add(mergeButton, BorderLayout.CENTER);

		JButton closeButton = new JButton("닫기");
		closeButton.addActionListener(e -> System.exit(0));
		frame.add(closeButton, BorderLayout.SOUTH);

		statusArea = new JTextArea();
		statusArea.setEditable(false);
		frame.add(new JScrollPane(statusArea), BorderLayout.EAST);

		frame.setVisible(true);
	}

	private class MergeButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String fileA = fileAPath.getText().trim();
			String fileB = fileBPath.getText().trim();
			String title = titleField.getText().trim();

			if (fileA.isEmpty() || fileB.isEmpty() || title.isEmpty()) {
				JOptionPane.showMessageDialog(frame, "모든 필드를 입력해 주세요.");
				return;
			}

			File mergedFile = new File(title + ".txt");

			try {
				statusArea.append("파일 병합 중...\n");
				statusArea.append("파일 A 읽는 중: " + fileA + "\n");
				statusArea.append("파일 B 읽는 중: " + fileB + "\n");

				BufferedReader readerA = new BufferedReader(new FileReader(fileA));
				BufferedReader readerB = new BufferedReader(new FileReader(fileB));
				BufferedWriter writer = new BufferedWriter(new FileWriter(mergedFile));

				String line;

				while ((line = readerA.readLine()) != null) {
					writer.write(line);
					writer.newLine();
				}
				readerA.close();

				statusArea.append("파일 A 내용 추가 완료.\n");

				while ((line = readerB.readLine()) != null) {
					writer.write(line);
					writer.newLine();
				}
				readerB.close();

				statusArea.append("파일 B 내용 추가 완료.\n");
				writer.close();

				JOptionPane.showMessageDialog(frame, "파일 병합 완료! 저장된 파일명: " + mergedFile.getName());

				statusArea.append("파일 병합 완료!\n");
			} catch (IOException ex) {
				statusArea.append("오류 발생: " + ex.getMessage() + "\n");
				JOptionPane.showMessageDialog(frame, "오류 발생: " + ex.getMessage());
			}
		}
	}
}
