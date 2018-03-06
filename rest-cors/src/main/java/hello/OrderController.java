package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
	
	private static final String template = "It is, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    Connection con;
    
    public OrderController() {
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dec2016","root","password");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @RequestMapping("/orders")
    public @ResponseBody ArrayList<Order> orders(
            @RequestParam(value="desc", required=false, defaultValue="demo") String desc, @RequestParam(value="amt", required=false, defaultValue="0.99") double amt) {
    	
    	ArrayList<Order> list = getOrders();
    	if(list.size() == 0) {
    		Order o1 = new Order(1,
                String.format(template, desc), amt);
    		list.add(o1);
    	}
    	/*Order o2 = new Order(counter.incrementAndGet(),
                String.format(template, "mobiles"), 1785.00);
    	Order o3 = new Order(counter.incrementAndGet(),
                String.format(template, "bags"), 2122.00);
    	list.add(o1);
    	list.add(o2);
    	list.add(o3);*/
    	
        return list;
    }
    
    
    private ArrayList<Order> getOrders() {
    	ArrayList<Order> list = new ArrayList();
    	try{
    		PreparedStatement ps = con.prepareStatement("select * from myorders where active=1");
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			Order order = new Order(rs.getLong(1),rs.getString(2),rs.getDouble(3));
    			list.add(order);
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    @RequestMapping("/neworder")
    public @ResponseBody ArrayList<Order> order(
            @RequestParam(value="desc", required=false, defaultValue="neworder") String desc, @RequestParam(value="amt", required=false, defaultValue="0.01") double amt) {

    	ArrayList<Order> list = new ArrayList<Order>();	
    	int x = addOrder(desc, amt);
    	
    	if(x > 0) {
    		list = getOrders();
    	}
    	
        return list;
    }
    
    private int addOrder(String desc, double amt) {
    	int x = 0;
    	try{
    		PreparedStatement ps = con.prepareStatement("insert into myorders (description, amount, active) values(?,?,?)");
    		ps.setString(1,  desc);
    		ps.setDouble(2, amt);
    		ps.setInt(3, 1);
    		x = ps.executeUpdate();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return x;
    }
    
    @RequestMapping("/delorder")
    public @ResponseBody ArrayList<Order> order(
            @RequestParam(value="id", required=false, defaultValue="0") int id) {

    	ArrayList<Order> list = new ArrayList<Order>();	
    	int x = delOrder(id);
    	
    	if(x > 0) {
    		list = getOrders();
    	}
    	
        return list;
    }
    
    private int delOrder(int id) {
    	int x = 0;
    	try{
    		PreparedStatement ps = con.prepareStatement("update myorders set active=0 where id=?");
    		ps.setInt(1,  id);
    		x = ps.executeUpdate();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return x;
    }
	
}
