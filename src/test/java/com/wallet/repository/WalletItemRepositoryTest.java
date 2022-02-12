package com.wallet.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemRepositoryTest {

	private static final Date DATE = new Date();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Conta de Luz";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);
	
	private Long walletItemId = null;
	private Long walletId = null;
	
	@Autowired
	WalletItemRepository repository;
	
	@Autowired
	WalletRepository walletRepository;

	@Before
	public void setUp() {
		
		Wallet w = new Wallet ();
		w.setName("Carteira Teste");
		w.setValue(BigDecimal.valueOf(200));
		walletRepository.save(w);
		
		WalletItem wi = new WalletItem (1L, w, DATE,TYPE, DESCRIPTION, VALUE);
		repository.save(wi);
		
		walletItemId = wi.getId();
		walletId = w.getId();
		
	}
	
	@After
	public void tearDown(){
		repository.deleteAll();
		walletRepository.deleteAll();
	}
	
	@Test
	public void testSave() {
		
		Wallet w = new Wallet ();
		w.setName("Carteira 1");
		w.setValue(BigDecimal.valueOf(500));
		walletRepository.save(w);
		
		WalletItem wi = new WalletItem (1L, w, DATE,TYPE, DESCRIPTION, VALUE);
		WalletItem response = repository.save(wi);
		
		assertNotNull(response);
		assertEquals(response.getWallet(), w);
		assertEquals(response.getDate(),DATE);
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getDescription(), DESCRIPTION);
		assertEquals(response.getValue(), VALUE);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testSaveInvalidWalletItem() {
		
		WalletItem wi = new WalletItem (null, null, DATE,TYPE, DESCRIPTION, null);
		repository.save(wi);
	}
	
	@Test
	public void testUpdate() {
		Optional <WalletItem> wi = repository.findById(walletItemId);
		String descrip = "New description...";
		
		WalletItem changed = wi.get();
		
		changed.setDescription(descrip);
		repository.save(changed);
		
		Optional <WalletItem> newWalletItem = repository.findById(walletItemId);
		assertEquals(descrip, newWalletItem.get().getDescription());
		
	}
	
	
	@Test
	public void deleteWalletItem() {
		Optional <Wallet> w = walletRepository.findById(walletId);	
		WalletItem wi = new WalletItem (null, w.get(),DATE,TYPE,DESCRIPTION,VALUE);
		repository.save(wi);
		
		repository.deleteById(wi.getId());
		
		Optional <WalletItem> response = repository.findById(walletItemId);
		
		assertFalse(response.isPresent());		
		
	}
	
	
	@Test
	public void testFindBetweenDates() {
		
		Optional <Wallet> w = walletRepository.findById(walletId);
		
		LocalDateTime localDateTime = DATE.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		Date currentDatePlusFiveDays = Date.from(localDateTime.plusDays(5).atZone(ZoneId.systemDefault()).toInstant());
		Date currentDatePlusSevenDays = Date.from(localDateTime.plusDays(7).atZone(ZoneId.systemDefault()).toInstant());
		
		repository.save(new WalletItem(null, w.get(),currentDatePlusFiveDays, TYPE,DESCRIPTION,VALUE));
		repository.save(new WalletItem(null, w.get(),currentDatePlusSevenDays, TYPE,DESCRIPTION,VALUE));
		
		
		PageRequest pg = PageRequest.of(0, 10); 
		Page<WalletItem> response = repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(walletId, DATE, currentDatePlusFiveDays, pg);
			
		
		assertEquals(response.getContent().size(),2);
		assertEquals(response.getTotalElements(), 2);
		assertEquals(response.getContent().get(0).getWallet().getId(), walletId);

	}
	
	@Test
	public void testFindByType() {
		List<WalletItem> response = repository.findByWalletIdAndType(walletId, TYPE);
		
		assertEquals(response.size(), 1);
		assertEquals (response.get(0).getType(), TYPE);
		
	}
	
	@Test
	public void testFindByTypeSd() {
		Optional <Wallet> w = walletRepository.findById(walletId);
		
		repository.save(new WalletItem(null, w.get(),DATE, TypeEnum.SD, DESCRIPTION, VALUE));
		
		List<WalletItem> response =  repository.findByWalletIdAndType(walletId, TypeEnum.SD);
		
		assertEquals(response.size(), 1);
		assertEquals(response.get(0).getType(), TypeEnum.SD);
		
	}
	
	@Test
	public void testSumByWallet() {
		Optional <Wallet> w = walletRepository.findById(walletId);
		
		repository.save(new WalletItem(null, w.get(), DATE, TYPE, DESCRIPTION,VALUE));
		
		BigDecimal response = repository.sumByWalletId(walletId);
		
		assertEquals(response.compareTo(BigDecimal.valueOf(215.8)), 0);
	
	}

	
	
	
	
	
	
	
}
