package com.wallet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

public interface WalletItemService {

	WalletItem save(WalletItem wi);
	
	Page<WalletItem> findBetweenDates(Long wallet, Date Start, Date end, int page);

	List<WalletItem> findByWalletAndType(Long wallet, TypeEnum en);

	BigDecimal sumByWalletId(Long l);

	Optional <WalletItem> findById(Long id);
	
	void deleteById(Long id);

}
