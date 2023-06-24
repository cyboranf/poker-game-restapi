package com.project.pokergame.mapper;

import com.project.pokergame.dto.TransactionsDTO;
import com.project.pokergame.model.Transactions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    TransactionsDTO transactionsToTransactionsDTO(Transactions transactions);

    @Mapping(source = "userAccountId", target = "userAccount.id")
    Transactions transactionsDTOToTransactions(TransactionsDTO transactionsDTO);
}
