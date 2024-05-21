import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class CodeEditorWithSnippets {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // 创建主窗体
        JFrame frame = new JFrame("代码编辑器（带代码片段）");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // 创建一个滚动面板以容纳文本区域，以便在内容超出时可以滚动查看
        JScrollPane scrollPane = new JScrollPane();
        frame.add(scrollPane, BorderLayout.CENTER);

        // 创建一个文本区域用于编辑代码
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // 使用等宽字体，更适合代码编辑
        scrollPane.setViewportView(textArea);

        // 创建代码片段映射
        Map<String, String> codeSnippets = new HashMap<>();
        codeSnippets.put("c", "public class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}\n");
        codeSnippets.put("f", "public class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}\n");

        // 为文本区域添加键盘监听器
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_SPACE) {
                    int caretPosition = textArea.getCaretPosition();
                    try {
                        String prefix = textArea.getText(caretPosition - 1, 1);
                        String codeSnippet = codeSnippets.get(prefix);
                        if (codeSnippet != null) {
                            textArea.replaceRange("", caretPosition - 1, caretPosition);
                            textArea.insert(codeSnippet, caretPosition - 1);
                        }
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // 显示窗口
        frame.setVisible(true);
    }
}

/*
javac -encoding UTF-8 xx.java
java xx
*/