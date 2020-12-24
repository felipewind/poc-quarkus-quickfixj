package com.helesto.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.helesto.core.StockExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.Message;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.fix44.ExecutionReport;

@ApplicationScoped
public class ExecutionReportService {

	private static final Logger LOG = LoggerFactory.getLogger(ExecutionReportService.class.getName());

	@Inject
	StockExchange stockExchange;

	public void executionReport(Message message, SessionID sessionID) {

		LOG.info("executionReport");

		ExecutionReport executionReport = new ExecutionReport();

		// // Tag 11 ClOrdID
		// executionReport.set(new ClOrdID(newOrderSingle.getClOrdID()));

		// // Tag 55 Symbol
		// executionReport.set(new Symbol(newOrderSingle.getSymbol()));

		// // Tag 54 Side
		// if (newOrderSingle.getSide()=='1') {
		// executionReport.set(new Side(Side.BUY));
		// } else {
		// executionReport.set(new Side(Side.SELL));
		// }

		// // Tag 60 TransactTime
		// executionReport.set(new TransactTime(LocalDateTime.now()));

		// // Tag 38 OrderQty
		// executionReport.set(new OrderQty(newOrderSingle.getOrderQty()));

		// // Tag 40 OrdType
		// executionReport.set(new OrdType(OrdType.LIMIT));

		// // Tag 44 Price
		// executionReport.set(new Price(newOrderSingle.getPrice()));

		// // Tag 1 Account
		// executionReport.setField(new Account(newOrderSingle.getAccount()));

		// // Tag 22 SecurityIDSource
		// executionReport.setField(new
		// SecurityIDSource(SecurityIDSource.EXCHANGE_SYMBOL));

		// // Tag 59 TimeInForce
		// executionReport.setField(new TimeInForce(TimeInForce.DAY));

		try {
			Session.sendToTarget(executionReport, sessionID);

		} catch (SessionNotFound e) {
			LOG.error(e.getMessage());
		}

	}

}
