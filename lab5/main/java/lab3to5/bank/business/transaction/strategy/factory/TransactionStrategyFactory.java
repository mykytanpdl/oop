package lab3to5.bank.business.transaction.strategy.factory;

import lab3to5.bank.business.enums.TransactionType;
import lab3to5.bank.business.transaction.strategy.TransactionStrategy;

public interface TransactionStrategyFactory {
    TransactionStrategy createStrategy(TransactionType type);
}