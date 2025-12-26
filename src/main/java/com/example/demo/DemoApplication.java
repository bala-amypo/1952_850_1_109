package com.example.demo;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.entity.RewardRule;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UserProfileRepository userRepository,
							   CreditCardRecordRepository cardRepository,
							   RewardRuleRepository ruleRepository,
							   PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.count() == 0) {
				// Create default user
				UserProfile user = new UserProfile();
				user.setUserId("USER_DEFAULT");
				user.setFullName("Default User");
				user.setEmail("user@example.com");
				user.setPassword(passwordEncoder.encode("password123"));
				user.setRole("USER");
				user.setActive(true);
				user = userRepository.save(user);

				// Create default credit card
				CreditCardRecord card = new CreditCardRecord();
				card.setUserId(user.getId());
				card.setCardName("Default Rewards Card");
				card.setIssuer("Default Bank");
				card.setCardType("REWARDS");
				card.setAnnualFee(0.0);
				card.setStatus("ACTIVE");
				card = cardRepository.save(card);

				// Create default reward rules
				RewardRule rule1 = new RewardRule();
				rule1.setCardId(card.getId());
				rule1.setCategory("GROCERY");
				rule1.setRewardType("CASHBACK");
				rule1.setMultiplier(2.0);
				rule1.setActive(true);
				ruleRepository.save(rule1);

				RewardRule rule2 = new RewardRule();
				rule2.setCardId(card.getId());
				rule2.setCategory("TRAVEL");
				rule2.setRewardType("POINTS");
				rule2.setMultiplier(3.0);
				rule2.setActive(true);
				ruleRepository.save(rule2);
			}
		};
	}
}
