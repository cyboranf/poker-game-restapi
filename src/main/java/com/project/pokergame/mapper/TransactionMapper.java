package com.project.pokergame.mapper;

import com.project.pokergame.dto.TransactionsDTO;
import com.project.pokergame.model.Transactions;
import com.project.pokergame.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "userAccount.id", target = "userAccountId")
    TransactionsDTO toDTO(Transactions transactions);

    @Mapping(target = "userAccount", ignore = true)
    Transactions DTO2Transactions(TransactionsDTO transactionsDTO);

    default UserAccount mapUserAccount(Long value) {
        if (value == null) {
            return null;
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(value);
        return userAccount;
    }
}
