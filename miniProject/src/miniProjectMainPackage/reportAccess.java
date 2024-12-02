package miniProjectMainPackage;

//import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class reportAccess {
	public JTable table_1;
	private JPanel panel;

	 private Connection con; 
	 private JTextField textTotalProfit;
	 private JTextField textTotalLoss;

	    public reportAccess(Connection con) {
	        this.con = con;  
	    }
	/**
	 * @wbp.parser.entryPoint
	 */
	 
	
	public void Access() {
		
		 JFrame reportWindow = new JFrame();
	        reportWindow.setBounds(100, 100,922,670); 
	        reportWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	        reportWindow.getContentPane().setLayout(null); 
	        // Using absolute layout for simplicity
	        JLabel lblNewLabel = new JLabel("Reports");
	    	lblNewLabel.setBounds(313, 9, 189, 69);
	    	lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 45));
	    	reportWindow.getContentPane().add(lblNewLabel);
	    	
	    	JButton btnlowStocks = new JButton("Low Stocks");
	    	btnlowStocks.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			PreparedStatement prestm = null; 
	    	        ResultSet rst = null; 
	    	        
	    	        try {
	    	           
	    	            if (con == null) {
	    	                System.out.println("Database connection is not established.");
	    	                return;
	    	            }

	    	            
	    	            prestm = con.prepareStatement("SELECT * FROM lowstock");
	    	            rst = prestm.executeQuery();

	    	          
	    	            table_1.setModel(DbUtils.resultSetToTableModel(rst));
	    	            
	    	        } catch (SQLException e1) {
	    	            e1.printStackTrace();
	    	        } finally {
	    	            
	    	            try {
	    	                if (rst != null) rst.close();
	    	                if (prestm != null) prestm.close();
	    	            } catch (SQLException e2) {
	    	                e2.printStackTrace();
	    	            }
	    	        }
	    		
	    		}
	    	});
	    	btnlowStocks.setFont(new Font("Tahoma", Font.BOLD, 17));
	    	btnlowStocks.setBounds(73, 205, 143, 45);
	    	reportWindow.getContentPane().add(btnlowStocks);
	    	
	    	JButton btnProfit = new JButton("Profit");
	    	btnProfit.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			PreparedStatement prestm = null; 
	    	        ResultSet rst = null; 
	    	        
	    	        try {
	    	           
	    	            if (con == null) {
	    	                System.out.println("Database connection is not established.");
	    	                return;
	    	            }

	    	          
	    	            prestm = con.prepareStatement("SELECT * FROM profit");
	    	            rst = prestm.executeQuery();

	    	           
	    	            table_1.setModel(DbUtils.resultSetToTableModel(rst));
	    	            
	    	        } catch (SQLException e1) {
	    	            e1.printStackTrace();
	    	        } finally {
	    	           	    	            try {
	    	                if (rst != null) rst.close();
	    	                if (prestm != null) prestm.close();
	    	            } catch (SQLException e2) {
	    	                e2.printStackTrace();
	    	            }
	    	        }
	    			
	    		}
	    	});
	    	btnProfit.setFont(new Font("Tahoma", Font.BOLD, 17));
	    	btnProfit.setBounds(73, 338, 143, 45);
	    	reportWindow.getContentPane().add(btnProfit);
	    	
	    	JButton btnLoss = new JButton("Loss");
	    	btnLoss.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			PreparedStatement prestm = null; 
	    	        ResultSet rst = null; 
	    	        
	    	        try {
	    	            if (con == null) {
	    	                System.out.println("Database connection is not established.");
	    	                return;
	    	            }

	    	            prestm = con.prepareStatement("SELECT * FROM loss");
	    	            rst = prestm.executeQuery();

	    	            table_1.setModel(DbUtils.resultSetToTableModel(rst));
	    	            
	    	        } catch (SQLException e1) {
	    	            e1.printStackTrace();
	    	        } finally {
	    	            try {
	    	                if (rst != null) rst.close();
	    	                if (prestm != null) prestm.close();
	    	            } catch (SQLException e2) {
	    	                e2.printStackTrace();
	    	            }
	    	        }
	    			
	    		}
	    	});
	    	btnLoss.setFont(new Font("Tahoma", Font.BOLD, 17));
	    	btnLoss.setBounds(73, 468, 143, 45);
	    	reportWindow.getContentPane().add(btnLoss);
	    	
	    	panel = new JPanel();
	    	panel.setBorder(new TitledBorder(null, "Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    	panel.setBounds(313, 169, 533, 437);
	    	reportWindow.getContentPane().add(panel);
	    	panel.setLayout(null);
	    	
	    	JScrollPane scrollPane = new JScrollPane();
	    	scrollPane.setBounds(28, 26, 477, 401);
	    	panel.add(scrollPane);
	    	
	    	table_1 = new JTable();
	    	scrollPane.setViewportView(table_1);
	    	
	    	JButton btnExit = new JButton("Exit");
	    	btnExit.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			
	    			reportWindow.dispose();  

	         

	    		}
	    	});
	    	btnExit.setFont(new Font("Tahoma", Font.BOLD, 17));
	    	btnExit.setBounds(772, 26, 126, 45);
	    	reportWindow.getContentPane().add(btnExit);
	    	
	    	textTotalProfit = new JTextField();
	    	textTotalProfit.setBounds(216, 108, 126, 25);
	    	reportWindow.getContentPane().add(textTotalProfit);
	    	textTotalProfit.setColumns(10);
	    	
	    	textTotalLoss = new JTextField();
	    	textTotalLoss.setColumns(10);
	    	textTotalLoss.setBounds(618, 108, 137, 26);
	    	reportWindow.getContentPane().add(textTotalLoss);
	    	
	    	JButton btnTotalLoss = new JButton("Total Loss");
	    	btnTotalLoss.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			
	    			 PreparedStatement prestm = null; 
	    		        ResultSet rst = null; 
	    		        try {
	    		            prestm = con.prepareStatement("SELECT SUM(loss) AS totalLoss FROM loss");
	    		            rst = prestm.executeQuery();

	    		            if (rst.next()) {  
	    		                double totalLoss = rst.getDouble("totalLoss");  
	    		                textTotalLoss.setText(String.valueOf(totalLoss));  	    		            }
	    		        } catch (SQLException e1) {
	    		            e1.printStackTrace();  
	    		        } finally {
	    		            try {
	    		                if (rst != null) rst.close();
	    		                if (prestm != null) prestm.close();
	    		            } catch (SQLException e1) {
	    		                e1.printStackTrace();
	    		            }
	    		        }
	    		}
	    	});
	    	btnTotalLoss.setFont(new Font("Tahoma", Font.BOLD, 16));
	    	btnTotalLoss.setBounds(471, 104, 137, 34);
	    	reportWindow.getContentPane().add(btnTotalLoss);
	    	
	    	JButton btnTotalProfit = new JButton("Total Profit");
	    	btnTotalProfit.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			 PreparedStatement prestm = null; 
	    		        ResultSet rst = null; 
	    		        try {
	    		            prestm = con.prepareStatement("SELECT SUM(Profit) AS totalProfit FROM profit");
	    		            rst = prestm.executeQuery();

	    		            if (rst.next()) {  
	    		                double totalProfit = rst.getDouble("totalProfit");  
	    		                textTotalProfit.setText(String.valueOf(totalProfit));  
	    		            }
	    		        } catch (SQLException e1) {
	    		            e1.printStackTrace();  // Handle the SQL exception
	    		        } finally {
	    		            try {
	    		                if (rst != null) rst.close();
	    		                if (prestm != null) prestm.close();
	    		            } catch (SQLException e1) {
	    		                e1.printStackTrace();
	    		            }
	    		        }
	    		}
	    	});
	    	btnTotalProfit.setFont(new Font("Tahoma", Font.BOLD, 16));
	    	btnTotalProfit.setBounds(58, 104, 137, 34);
	    	reportWindow.getContentPane().add(btnTotalProfit);

	    
	        reportWindow.setVisible(true);
	        
	        
		
	}
}
