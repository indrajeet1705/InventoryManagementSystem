package miniProjectMainPackage;
import java.sql.*;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import java.util.HashMap;
public class interFace {
	private JFrame frame;
	private JTable table;
	private JTextField textSellid;
	private JTextField textSellquantity;
	private JTextField textPname;
	private JTextField textQuantity;
	private JTextField textPrice;
	private JTextField textSearchid;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interFace window = new interFace();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public interFace() {
		initialize();
		//initialize();
		buildconnection();
		loadTable();
	}

	

Connection con;
PreparedStatement prestm;
ResultSet rst;
private JTextField textUnit;
private JTextField textSellingPrice;


public void buildconnection() {
	try {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventoryalertsystem","root","indrajeet1705");
		System.out.println("Done with the stable connection");
		reportAccess report = new reportAccess(con);
		report.Access();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void loadTable() {
	try {
		prestm = con.prepareStatement("select * from inventorytable");
		rst=prestm.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rst));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}





public class ItemUnitHandler {
    private HashMap<String, String> grainUnits = new HashMap<>();
    private HashMap<String, String> liquidUnits = new HashMap<>();

    public ItemUnitHandler() {
        // Initialize grain units
        grainUnits.put("wheat", "kg");
        grainUnits.put("rice", "kg");
        grainUnits.put("corn", "kg");
        grainUnits.put("oats", "kg");
        grainUnits.put("millet", "kg");
        grainUnits.put("quinoa", "kg");
        grainUnits.put("sorghum", "kg");
        grainUnits.put("rye", "kg");
        grainUnits.put("buckwheat", "kg");
        
   

       
        liquidUnits.put("milk", "liter");
        liquidUnits.put("water", "liter");
        liquidUnits.put("juice", "liter");
        liquidUnits.put("oil", "liter");
        liquidUnits.put("vinegar", "liter");
        liquidUnits.put("soda", "liter");
        liquidUnits.put("honey", "ml");
        liquidUnits.put("detergent", "liter");
        liquidUnits.put("shampoo", "ml");
        liquidUnits.put("wine", "liter");
        // Add other liquids...
    }

    // Non-static method to set the unit based on item name
    public void setUnitForItem(String itemName, JTextField unitTextField) {
        String unit = grainUnits.get(itemName.toLowerCase());
        if (unit != null) {
            unitTextField.setText(unit);
        } else {
            unit = liquidUnits.get(itemName.toLowerCase());
            if (unit != null) {
                unitTextField.setText(unit);
            } else {
                unitTextField.setText(""); // Clear if no match found
            }
        }
    }
}

private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 922, 670);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("INVENTORY ALERT  SYSTEM");
	lblNewLabel.setBounds(181, 10, 567, 51);
	lblNewLabel.setFont(new Font("Modern No. 20", Font.BOLD, 35));
	frame.getContentPane().add(lblNewLabel);
	
	JPanel panel = new JPanel();
	panel.setBounds(21, 60, 357, 258);
	panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Items", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	frame.getContentPane().add(panel);
	panel.setLayout(null);
	
	JLabel lblNewLabel_1 = new JLabel("Quantity :");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblNewLabel_1.setBounds(10, 90, 114, 35);
	panel.add(lblNewLabel_1);
	
	JLabel lblNewLabel_1_1 = new JLabel("Cost per Item :");
	lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblNewLabel_1_1.setBounds(10, 135, 130, 46);
	panel.add(lblNewLabel_1_1);
	
	JLabel lblNewLabel_1_2 = new JLabel("Product Name :");
	lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblNewLabel_1_2.setBounds(10, 45, 130, 35);
	panel.add(lblNewLabel_1_2);
	
	JButton btnAdditem = new JButton("Add Item");
	btnAdditem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String Name,Quantity,Price,Unit;
			
			Name = textPname.getText();
			Quantity=textQuantity.getText();
			Price = textPrice.getText();
			Unit=textUnit.getText();
			try {
				prestm = con.prepareStatement("insert into inventorytable (ProductName,Quantity,CostperItem,units)  values (?,?,?,?)");
				
				prestm.setString(1, Name);
				prestm.setString(2, Quantity);
				prestm.setString(3, Price);
				prestm.setString(4, Unit);

				prestm.executeUpdate();
				
		
			    
				loadTable();
			     
				textPname.setText("");
				textQuantity.setText("");
				textPrice.setText("");
				textUnit.setText("");
				textPname.requestFocus();
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		}
	});
	btnAdditem.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnAdditem.setBounds(20, 191, 120, 35);
	panel.add(btnAdditem);
	
	
	
	
	textPname = new JTextField();
	ItemUnitHandler handler = new ItemUnitHandler();
	textPname.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			  String itemName = textPname.getText(); // Get the product name entered
		        handler.setUnitForItem(itemName, textUnit); // Automatically set the unit
		}
	});
	textPname.setBounds(134, 45, 213, 29);
	panel.add(textPname);
	textPname.setColumns(10);
	
	textQuantity = new JTextField();
	textQuantity.setColumns(10);
	textQuantity.setBounds(100, 95, 80, 29);
	panel.add(textQuantity);
	
	textPrice = new JTextField();
	textPrice.setColumns(10);
	textPrice.setBounds(134, 146, 213, 29);
	panel.add(textPrice);
	
	JButton btnClear = new JButton("Clear");
	btnClear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		    
			textPname.setText("");
			textQuantity.setText("");
			textPrice.setText("");
			textUnit.setText("");
			textPname.requestFocus();
			
			
		}
	});
	btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnClear.setBounds(201, 191, 120, 35);
	panel.add(btnClear);
	
	textUnit = new JTextField();
	
	textUnit.setColumns(10);
	textUnit.setBounds(267, 95, 80, 29);
	panel.add(textUnit);
	
	JLabel lblNewLabel_4 = new JLabel("Unit :");
	lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblNewLabel_4.setBounds(196, 96, 61, 22);
	panel.add(lblNewLabel_4);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBounds(21, 328, 357, 165);
	panel_1.setBorder(new TitledBorder(null, "Item Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	frame.getContentPane().add(panel_1);
	panel_1.setLayout(null);
	
	JLabel lblNewLabel_2 = new JLabel("Enter Item Id :");
	lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblNewLabel_2.setBounds(10, 34, 121, 33);
	panel_1.add(lblNewLabel_2);
	
	JButton btnSearch = new JButton("Search ");
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			
			String searchId =textSearchid.getText();
			try {
				
				
				prestm = con.prepareStatement("select * from inventorytable where ID=?");
				prestm.setString(1, searchId);
				
				 rst = prestm.executeQuery();
				 
				 if(rst.next()) {
					
					 textPname.setText(rst.getString(2));
					 textQuantity.setText(rst.getString(3));
				   	textPrice.setText(rst.getString(4));
					textUnit.setText(rst.getString(5));	 
				 }
				 else {
					 textPname.setText("");
						textQuantity.setText("");
						textPrice.setText("");
						textUnit.setText("");
						  JOptionPane.showMessageDialog(null, "Product ID not found in inventory", "Alert", JOptionPane.WARNING_MESSAGE);
					        
						textPname.requestFocus();
				 }
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			 
			 
		}
	});
	btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnSearch.setBounds(49, 89, 109, 49);
	panel_1.add(btnSearch);
	
	textSearchid = new JTextField();
	textSearchid.setColumns(10);
	textSearchid.setBounds(141, 34, 156, 29);
	panel_1.add(textSearchid);
	
	JButton btnSearchclear = new JButton("Clear");
	btnSearchclear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			textSearchid.setText("");
			
			
			textSearchid.requestFocus();
			

		}
	});
	btnSearchclear.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnSearchclear.setBounds(204, 89, 109, 49);
	panel_1.add(btnSearchclear);
	
	JPanel panel_2 = new JPanel();
	panel_2.setBounds(21, 503, 357, 120);
	panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Modify Records", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	frame.getContentPane().add(panel_2);
	panel_2.setLayout(null);
	
	JButton btnDelete = new JButton("Delete");
	btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {  
			String id = textSearchid.getText();
			
			try {
				prestm = con.prepareStatement("delete from inventorytable where ID=?");
				
				prestm.setString(1,id);
				prestm.executeUpdate();
				loadTable();
				
				   
				textPname.setText("");
				textQuantity.setText("");
				textPrice.setText("");
				
				textPname.requestFocus();
				
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
		}
	});
	btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnDelete.setBounds(200, 26, 116, 50);
	panel_2.add(btnDelete);
	
	JButton btnUpdate = new JButton("Update");
	btnUpdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
            String Name,Quantity,Price,id,Unit;
			
			Name = textPname.getText();
			Quantity=textQuantity.getText();
			Price = textPrice.getText();
			id=textSearchid.getText();
			Unit=textUnit.getText();
			
			try {
				
				prestm = con.prepareStatement("update inventorytable set ProductName=?,Quantity=?,CostperItem=?,Units = ? where ID =?");
				
				prestm.setString(1, Name);
				prestm.setString(2, Quantity);
				prestm.setString(3, Price);
				prestm.setString(4, Unit);
				prestm.setString(5,id);
			

				
				prestm.executeUpdate();
				
			JOptionPane.showMessageDialog(null, "ITEM UPDATED !!");
			    
				loadTable();
			     
				textPname.setText("");
				textQuantity.setText("");
				textPrice.setText("");
			textUnit.setText("");
				
				textPname.requestFocus();
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		}
		}
	);
	btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnUpdate.setBounds(28, 26, 116, 50);
	panel_2.add(btnUpdate);
	
	JPanel panel_3 = new JPanel();
	panel_3.setBorder(new TitledBorder(null, "Inventory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_3.setBounds(408, 60, 490, 387);
	frame.getContentPane().add(panel_3);
	panel_3.setLayout(null);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 31, 470, 346);
	panel_3.add(scrollPane);
	
	table = new JTable();
	table.setGridColor(new Color(0, 0, 0));
	table.setBorder(new LineBorder(new Color(0, 0, 0)));
	scrollPane.setViewportView(table);
	
	JButton btnExit = new JButton("EXIT");
	btnExit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	btnExit.setFont(new Font("Tahoma", Font.BOLD, 17));
	btnExit.setBounds(813, 10, 85, 39);
	frame.getContentPane().add(btnExit);
	
	JPanel panel_4 = new JPanel();
	panel_4.setBorder(new TitledBorder(null, "Sell Item", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel_4.setBounds(408, 475, 490, 148);
	frame.getContentPane().add(panel_4);
	panel_4.setLayout(null);
	
	JLabel lblNewLabel_3 = new JLabel("Enter Item Id :");
	lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblNewLabel_3.setBounds(30, 24, 124, 43);
	panel_4.add(lblNewLabel_3);
	
	textSellid = new JTextField();
	textSellid.setBounds(153, 33, 124, 29);
	panel_4.add(textSellid);
	textSellid.setColumns(10);
	
	JLabel lblNewLabel_3_1 = new JLabel("Quantity :");
	lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblNewLabel_3_1.setBounds(30, 60, 124, 43);
	panel_4.add(lblNewLabel_3_1);
	
	textSellquantity = new JTextField();
	textSellquantity.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {

			String sellId = textSellid.getText();
			String sellQuantity = textSellquantity.getText();
			
			try {
				prestm = con.prepareStatement("select * from inventorytable where ID=?");
				
				prestm.setString(1, sellId);

				rst = prestm.executeQuery();
				
				
				
				if(rst.next()) {
					if(Integer.parseInt(sellQuantity)>Integer.parseInt(rst.getString(3))) {
						JOptionPane.showMessageDialog(null,"NOT ENOUGH STOCK!!", "Alert", JOptionPane.WARNING_MESSAGE);
					}
					if(Integer.parseInt(rst.getString(3))<3 ) {
						
						JOptionPane.showMessageDialog(null,"REFILL YOUR STOCK IMMEDIATELY !!!", "Alert", JOptionPane.WARNING_MESSAGE);
					}
                    if(Integer.parseInt(sellQuantity)<0) {
						
						JOptionPane.showMessageDialog(null,"Quantity can't be Negative!!!", "Alert", JOptionPane.WARNING_MESSAGE);
					}
					
					
					if(Integer.parseInt(sellQuantity)<=Integer.parseInt(rst.getString(3))) {
						textPname.setText(rst.getString(2));
						textQuantity.setText(rst.getString(3));
						textPrice.setText(rst.getString(4));
						textUnit.setText(rst.getString(5));
					}else {
						textPname.setText("");
						textQuantity.setText("");
						textPrice.setText("");
						
						textPname.requestFocus();
					}
					
				}else {
					textPname.setText("");
					textQuantity.setText("");
					textPrice.setText("");
					
					textPname.requestFocus();
				}
				
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	});
	textSellquantity.setColumns(10);
	textSellquantity.setBounds(153, 69, 124, 29);
	panel_4.add(textSellquantity);
	
	JButton btnSellitem = new JButton("Sell Items");
	btnSellitem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String name,quantity, id,sellingPrice,price;
			
			name = textPname.getText();
			quantity = textQuantity.getText();
			price = textPrice.getText();
			sellingPrice=textSellingPrice.getText();
			id = textSellid.getText();
			String sellQuantity = textSellquantity.getText();
			
			Integer diffQuantity = Integer.parseInt(quantity) - Integer.parseInt(sellQuantity);
			Integer Profit = (Integer.parseInt(sellQuantity))*(Integer.parseInt(sellingPrice)-Integer.parseInt(price));
//			Integer Loss=(Integer.parseInt(sellQuantity))*(Integer.parseInt(sellingPrice)-Integer.parseInt(price));
			
			try {
				prestm = con.prepareStatement("update InventoryTable set Quantity=? where ID=?");
				prestm.setString(1, diffQuantity.toString());
				prestm.setString(2, id);
				
				prestm.executeUpdate();
				
				if (Profit>=0) {
				prestm = con.prepareStatement("insert into  profit values(?,?,?)");
			    prestm.setString(1,id);
			    prestm.setString(2,name);
			    prestm.setInt(3,Profit);
				prestm.executeUpdate();
				}
				else {
				prestm = con.prepareStatement("insert into  loss values(?,?,?)");
			    prestm.setString(1,id);
			    prestm.setString(2,name);
			    prestm.setInt(3,Profit);
				prestm.executeUpdate();
				}
			//	JOptionPane.showMessageDialog(null,"Items Sold!!");
				
				loadTable();
				
				textPname.setText("");
				textQuantity.setText("");
				textPrice.setText("");
				textUnit.setText("");
				
				textPname.requestFocus();
				
						
			
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	});


	btnSellitem.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnSellitem.setBounds(331, 32, 136, 37);
	panel_4.add(btnSellitem);
	
	JButton btnSellclear = new JButton("Clear");
	btnSellclear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			 
			textSellid.setText("");
			textSellquantity.setText("");
			textSellingPrice.setText("");
			textSellid.requestFocus();
			

		}
	});
	btnSellclear.setFont(new Font("Tahoma", Font.BOLD, 15));
	btnSellclear.setBounds(331, 81, 136, 37);
	panel_4.add(btnSellclear);
	
	JLabel lblNewLabel_5 = new JLabel("Selling Price :");
	lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
	lblNewLabel_5.setBounds(30, 113, 113, 20);
	panel_4.add(lblNewLabel_5);
	
	textSellingPrice = new JTextField();
	textSellingPrice.setColumns(10);
	textSellingPrice.setBounds(153, 104, 124, 29);
	panel_4.add(textSellingPrice);
	
	
	
	JButton btnReport = new JButton("Report");
	btnReport.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		
			
			reportAccess ac= new reportAccess(con);
			  
			  ac.Access();
		        
		}
	});
	btnReport.setFont(new Font("Tahoma", Font.BOLD, 17));
	btnReport.setBounds(31, 11, 111, 39);
	frame.getContentPane().add(btnReport);
	
}
}


