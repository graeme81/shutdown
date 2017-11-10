import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class Closer {

	protected Shell shlCloser;
	private Text txtHours;
	private Text txtMins;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Closer window = new Closer();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCloser.open();
		shlCloser.layout();
		while (!shlCloser.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCloser = new Shell();
		shlCloser.setSize(358, 317);
		shlCloser.setText("Closer");
		
		Button btnOne = new Button(shlCloser, SWT.NONE);
		btnOne.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("shutdown -s -t 3600");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOne.setBounds(10, 10, 89, 62);
		btnOne.setText("1 Hour");
		
		Button btnTwo = new Button(shlCloser, SWT.NONE);
		btnTwo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("shutdown -s -t 7200");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTwo.setText("2 Hours");
		btnTwo.setBounds(241, 10, 89, 62);
		
		Button btnOneHalf = new Button(shlCloser, SWT.NONE);
		btnOneHalf.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("shutdown -s -t 5400");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOneHalf.setText("1.5 Hours");
		btnOneHalf.setBounds(125, 10, 89, 62);
		
		Button btnClose = new Button(shlCloser, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setText("Exit");
		btnClose.setBounds(81, 224, 168, 34);
		
		Button btnCancel = new Button(shlCloser, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("shutdown -A");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				txtHours.setText("0");
				txtMins.setText("0");
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(81, 174, 168, 37);
		
		txtHours = new Text(shlCloser, SWT.BORDER);
		txtHours.setText("0");
		txtHours.setBounds(114, 95, 55, 25);
		
		txtMins = new Text(shlCloser, SWT.BORDER);
		txtMins.setText("0");
		txtMins.setBounds(114, 126, 55, 25);
		
		Button btnCustom = new Button(shlCloser, SWT.NONE);
		btnCustom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Runtime runtime = Runtime.getRuntime();
				int hours, mins;
				
				try{
					if (txtHours.getText()==""){
						txtHours.setText("0");
					}
					hours = Integer.parseInt(txtHours.getText());
				}catch(Exception exc){
					MessageDialog.openError(shlCloser,"Error", "Bad Hour Input");
					return;
				}
				
				try{
					if (txtMins.getText()==""){
						txtMins.setText("0");
					}
					mins = Integer.parseInt(txtMins.getText());
				}catch(Exception exc){
					MessageDialog.openError(shlCloser,"Error", "Bad Minute Input");
					return;
				}
				
				
				
				hours = Integer.parseInt(txtHours.getText());
				mins = Integer.parseInt(txtMins.getText());
				int time = ((hours*60) + mins) * 60;
				try {
					runtime.exec("shutdown -s -t "+ time);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCustom.setBounds(190, 89, 89, 62);
		btnCustom.setText("Custom");
		
		Label lblHours = new Label(shlCloser, SWT.NONE);
		lblHours.setAlignment(SWT.RIGHT);
		lblHours.setBounds(48, 98, 55, 15);
		lblHours.setText("Hours:");
		
		Label lblMinutes = new Label(shlCloser, SWT.NONE);
		lblMinutes.setAlignment(SWT.RIGHT);
		lblMinutes.setBounds(48, 129, 55, 15);
		lblMinutes.setText("Minutes:");
		

	}
}
