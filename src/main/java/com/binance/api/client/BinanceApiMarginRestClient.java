package com.binance.api.client;

import com.binance.api.client.domain.TransferType;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.*;

import java.util.List;

public interface BinanceApiMarginRestClient {
    /**
     * Get current margin account information using default parameters.
     */
    MarginAccount getAccount();

    /**
     * Get all open orders on margin account for a symbol.
     *
     * @param orderRequest order request parameters
     */
    List<Order> getOpenOrders(OrderRequest orderRequest);

    /**
     * Send in a new margin order.
     *
     * @param order the new order to submit.
     * @return a response containing details about the newly placed order.
     */
    NewOrderResponse newOrder(NewOrder order);

    /**
     * Cancel an active margin order.
     *
     * @param cancelOrderRequest order status request parameters
     */
    CancelOrderResponse cancelOrder(CancelOrderRequest cancelOrderRequest);

    /**
     * Check margin order's status.
     * @param orderStatusRequest order status request options/filters
     *
     * @return an order
     */
    Order getOrderStatus(OrderStatusRequest orderStatusRequest);

    /**
     * Get margin trades for a specific symbol.
     *
     * @param symbol symbol to get trades from
     * @return a list of trades
     */
    List<Trade> getMyTrades(String symbol);

    // User stream endpoints

    /**
     * Start a new user data stream.
     *
     * @return a listen key that can be used with data streams
     */
    String startUserDataStream();

    /**
     * PING a user data stream to prevent a time out.
     *
     * @param listenKey listen key that identifies a data stream
     */
    void keepAliveUserDataStream(String listenKey);

    /**
     * Execute transfer between spot account and margin account
     * @param asset asset to repay
     * @param amount amount to repay
     * @return transaction id
     */
    MarginTransaction transfer(String asset, String amount, TransferType type);

    /**
     * Apply for a loan
     * @param asset asset to repay
     * @param amount amount to repay
     * @return transaction id
     */
    MarginTransaction borrow(String asset, String amount);

    /**
     * Query loan record
     * @param asset asset to query
     * @return repay records
     */
    RepayQueryResult queryRepay(String asset, long startTime);

    /**
     * Query max borrowable
     * @param asset asset to query
     * @return max borrowable
     */
    MaxBorrowableQueryResult queryMaxBorrowable(String asset);

    /**
     * Query loan record
     * @param asset asset to query
     * @param txId the tranId in POST /sapi/v1/margin/repay
     * @return loan records
     */
    RepayQueryResult queryRepay(String asset, String txId);

    /**
     * Repay loan for margin account
     * @param asset asset to repay
     * @param amount amount to repay
     * @return transaction id
     */
    MarginTransaction repay(String asset, String amount);

    /**
     * Query loan record
     * @param asset asset to query
     * @param txId the tranId in POST /sapi/v1/margin/loan
     * @return loan records
     */
    LoanQueryResult queryLoan(String asset, String txId);

    /**
     * Get all account orders; active, canceled, or filled.
     *
     * @param orderRequest order request parameters
     * @return a list of all account orders
     */
    List<Order> getAllOrders(AllOrdersRequest orderRequest);

    /**
     * Get trades for a specific account and symbol.
     *
     * @param symbol symbol to get trades from
     * @param limit  default 500; max 1000
     * @param fromId TradeId to fetch from. Default gets most recent trades.
     * @return a list of trades
     */
    List<Trade> getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp);

    /**
     * Get trades for a specific account and symbol.
     *
     * @param symbol symbol to get trades from
     * @param limit  default 500; max 1000
     * @return a list of trades
     */
    List<Trade> getMyTrades(String symbol, Integer limit);
}
