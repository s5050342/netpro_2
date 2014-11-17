package com.min;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class WebNetWork {

	private JFrame jf;
	private JTextField jt,jt1;
	private JEditorPane dis,dis1;
	private JButton btnGet;
	private JScrollPane js,js1,js2,js3;
	private JTextArea ja, ja1;
	//private Stack<String> stack = new Stack<String>();

	public static void main(String[] args) {
		new WebNetWork();
	}

	public WebNetWork() {
		jf = new JFrame("Nawamin Thanawattananon s5050342@kmitl.ac.th - Java Web Browser (Network Programming Class Assignment)");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setSize(1000,1000);

		jt = new JTextField();

		jt1 = new JTextField();

		btnGet = new JButton("GET");
		btnGet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String url = jt.getText().toLowerCase();
				String url1 = jt1.getText().toLowerCase();
				if(!url.startsWith("http://") && !url.startsWith("https://"))
					url = "http://" + url;
				if(!url1.startsWith("http://") && !url1.startsWith("https://"))
					url1 = "http://" + url1;
				setPage(url);
				setPage2(url1);
			}
		});


		dis = new JEditorPane();
		dis.setEditable(false);
		dis.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					jt.setText(event.getURL().toString());
				}
			}
		});

		dis1 = new JEditorPane();
		dis1.setEditable(false);
		dis1.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					jt1.setText(event.getURL().toString());
				}
			}
		});

		ja = new JTextArea();
		ja.setLineWrap(true);
		ja.setEditable(false);
		ja.setWrapStyleWord(true);

		ja1 = new JTextArea();
		ja1.setLineWrap(true);
		ja1.setEditable(false);
		ja1.setWrapStyleWord(true);


		js = new JScrollPane(dis);
		js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js1 = new JScrollPane(dis1);
		js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js2 = new JScrollPane(ja);
		js2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js3 = new JScrollPane(ja1);
		js3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		js3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		
		
		
		JLabel la= new JLabel("URL :");
		JPanel j1 = new JPanel(new BorderLayout());
		//j1.add(/* Btn Back*/, BorderLayout.WEST);
		j1.add(jt, BorderLayout.CENTER);
		j1.add(la, BorderLayout.WEST);

		JLabel la1 = new JLabel("URL :");
		JPanel j2 = new JPanel(new BorderLayout());
		j2.add(jt1, BorderLayout.CENTER);
		j2.add(la1, BorderLayout.WEST);
		//j1.add(/* Btn Back*/, BorderLayout.EAST);

		JPanel j3 = new JPanel(new BorderLayout());
		j3.add(j1, BorderLayout.NORTH);
		j3.add(js, BorderLayout.CENTER);

		JPanel j4 = new JPanel(new BorderLayout());
		j4.add(j2, BorderLayout.NORTH);
		j4.add(js1, BorderLayout.CENTER);

		JPanel j5 = new JPanel(new BorderLayout());
		j5.add(j3, BorderLayout.WEST);
		j5.add(j4, BorderLayout.EAST);

		JPanel j6 = new JPanel(new BorderLayout());
		j6.add(js2, BorderLayout.WEST);
		j6.add(js3, BorderLayout.EAST);

		jf.add(btnGet, BorderLayout.NORTH);
		jf.add(j5, BorderLayout.CENTER);
		jf.add(j6, BorderLayout.SOUTH);

		jf.setVisible(true);

		setSize();

	}

	private void setPage(final String url) {
		setText(ja, "THREAD 1", "Start");
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					setText(ja, "THREAD 1", "RUN URL = "+url);
					printHeader(ja1, "THREAD 1", url);
					dis.setPage(url);
				} catch (IOException e) {
					e.printStackTrace();
				}
				setText(ja, "THREAD 1", "Teminate");
			}
		});
		t.start();
	}

	private void setPage2(final String url) {
		setText(ja, "THREAD 2", "Start");
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					setText(ja, "THREAD 2", "RUN URL = "+url);
					printHeader(ja1, "THREAD 2", url);
					dis1.setPage(url);
				} catch (IOException e) {
					e.printStackTrace();
				}
				setText(ja, "THREAD 2", "Teminate");
			}
		});
		t.start();
	}

	private void setText(JTextArea j, String name, String m) {
		j.append(name + " : " + m + "\r\n");
		j.setCaretPosition(j.getText().length());
	}
	
	private void setSize() {
		Dimension d1 = jf.getSize();
		Dimension d2 = new Dimension(d1.width/2, d1.height/3);
		js.setPreferredSize(d2);
		js1.setPreferredSize(d2);
		js2.setPreferredSize(d2);
		js3.setPreferredSize(d2);
	}
	
	private void printHeader(JTextArea j, String name, String url) {
		try {
			URL obj = new URL(url);
			URLConnection conn = obj.openConnection();
		    Map<String, List<String>> map = conn.getHeaderFields();
		    for(Entry<String, List<String>> a : map.entrySet()) {
		    	setText(j, name, a.getKey() + ": " + a.getValue());
		    }
		    List<String> contentLength = map.get("Content-Length");
            if (contentLength == null) {
            	setText(j, name, "'Content-Length' doesn't present in Header!");
            } else {
                for (String header : contentLength) {
                	setText(j, name, "Content-Lenght: " + header);
                }
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
