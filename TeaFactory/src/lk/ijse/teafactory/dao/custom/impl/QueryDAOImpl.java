package lk.ijse.teafactory.dao.custom.impl;


import lk.ijse.teafactory.dao.custom.OrderDAO;
import lk.ijse.teafactory.dao.custom.OrderDetailDAO;
import lk.ijse.teafactory.dao.custom.ProductDAO;
import lk.ijse.teafactory.dao.custom.QueryDAO;
import lk.ijse.teafactory.db.DBConnection;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import lk.ijse.teafactory.model.OrderDTO;
import lk.ijse.teafactory.model.OrderdetailDTO;
import lk.ijse.teafactory.model.PlaceOrderDTO;
import lk.ijse.teafactory.model.ProductDTO;
import lk.ijse.teafactory.modelold.OrderDetailModel;
import lk.ijse.teafactory.modelold.OrderModel;
import lk.ijse.teafactory.modelold.ProductModel;
import lk.ijse.teafactory.tdm.CartDetail;
import lk.ijse.teafactory.tdm.Order;
import lk.ijse.teafactory.tdm.PlaceOrder;
import lk.ijse.teafactory.tdm.Product;

/**
 * author - kavindi
 * version - 1.0.0 10:32 PM 2/7/2023
 **/
public class QueryDAOImpl implements QueryDAO {

    public  boolean placeOrder(PlaceOrderDTO placeOrderDTO) throws SQLException, ClassNotFoundException {
       ProductDAO productDAO = new ProductDAOImpl();
       OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            OrderDAO orderDAO = new OrderDAOImpl();

            boolean isOrderAdded = orderDAO.save(new OrderDTO(placeOrderDTO.getOrderId(), LocalDate.now(), placeOrderDTO.getCustomerId()));
            if (isOrderAdded) {
                boolean isUpdated = updateQty(placeOrderDTO.getOrderDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = saveOrderDetails(placeOrderDTO.getOrderDetails());
                    if (isOrderDetailAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }

            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

    }

    public  boolean updateQty(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
       ProductDAO productDAO = new ProductDAOImpl();

        for (CartDetail cartDetail : cartDetails) {
            if (productDAO.update(new ProductDTO(cartDetail.getProductCode(),cartDetail.getDescription(),cartDetail.getUnitPrice(),cartDetail.getQty()))) {
                return false;
            }
        }
        return true;
    }

    public boolean saveOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

        for (CartDetail cartDetail : cartDetails) {
            if (!orderDetailDAO.save(new OrderdetailDTO(cartDetail.getOrderId(),cartDetail.getProductCode(),cartDetail.getQty(),cartDetail.getUnitPrice()))) {
                return false;
            }
        }
        return true;
    }
}
