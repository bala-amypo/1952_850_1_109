credit-card-reward-maximizer/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── demo/
│   │   │               ├── CreditCardMaximizerApplication.java
│   │   │               ├── config/
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller/
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── UserProfileController.java
│   │   │               │   ├── CreditCardController.java
│   │   │               │   ├── RewardRuleController.java
│   │   │               │   ├── PurchaseIntentController.java
│   │   │               │   └── RecommendationController.java
│   │   │               ├── dto/
│   │   │               │   ├── LoginRequest.java
│   │   │               │   ├── RegisterRequest.java
│   │   │               │   └── JwtResponse.java
│   │   │               ├── entity/
│   │   │               │   ├── UserProfile.java
│   │   │               │   ├── CreditCardRecord.java
│   │   │               │   ├── RewardRule.java
│   │   │               │   ├── PurchaseIntentRecord.java
│   │   │               │   └── RecommendationRecord.java
│   │   │               ├── exception/
│   │   │               │   ├── ResourceNotFoundException.java
│   │   │               │   └── BadRequestException.java
│   │   │               ├── repository/
│   │   │               │   ├── UserProfileRepository.java
│   │   │               │   ├── CreditCardRecordRepository.java
│   │   │               │   ├── RewardRuleRepository.java
│   │   │               │   ├── PurchaseIntentRecordRepository.java
│   │   │               │   └── RecommendationRecordRepository.java
│   │   │               ├── security/
│   │   │               │   └── JwtUtil.java
│   │   │               ├── service/
│   │   │               │   ├── UserProfileService.java
│   │   │               │   ├── CreditCardService.java
│   │   │               │   ├── RewardRuleService.java
│   │   │               │   ├── PurchaseIntentService.java
│   │   │               │   ├── RecommendationEngineService.java
│   │   │               │   └── impl/
│   │   │               │       ├── UserProfileServiceImpl.java
│   │   │               │       ├── CreditCardServiceImpl.java
│   │   │               │       ├── RewardRuleServiceImpl.java
│   │   │               │       ├── PurchaseIntentServiceImpl.java
│   │   │               │       └── RecommendationEngineServiceImpl.java
│   │   │               └── servlet/
│   │   │                   └── SimpleStatusServlet.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── demo/
│                       ├── CreditCardRewardMaximizerTest.java
│                       └── TestResultListener.java
└── pom.xml