# History

* Spring Framework Testing Context
    * JUnit 4 Laurel Test [HearingInterpreterTest](src/test/java/org/springframework/samples/petclinic/sfg/junit4/HearingInterpreterTest.java)
        * @RunWith(SpringRunner.class)
        * @ContextConfiguration(classes = {BaseConfig.class, LaurelConfig.class})
    * Assignment - JUnit 4 Yanny Test [HearingInterpreterYannyTest](src/test/java/org/springframework/samples/petclinic/sfg/junit4/HearingInterpreterYannyTest.java)
    * JUnit 5 Laurel Test [HearingInterpreterLaurelTest](src/test/java/org/springframework/samples/petclinic/sfg/junit5/HearingInterpreterLaurelTest.java)
        * @SpringJUnitConfig(classes = {BaseConfig.class, LaurelConfig.class})
    * Assignment - JUnit 5 Yanny Test [HearingInterpreterYannyTest](src/test/java/org/springframework/samples/petclinic/sfg/junit5/HearingInterpreterYannyTest.java)
    * Using Inner(Nested) Class Configuration [HearingInterpreterNestedClassTest](src/test/java/org/springframework/samples/petclinic/sfg/junit5/HearingInterpreterNestedClassTest.java)
        * @SpringJUnitConfig(classes = HearingInterpreterNestedClassTest.TestConfig.class)
    * Using Component Scans
        * @Configuration @ComponentScan("org.springframework.samples.petclinic.sfg") static class TestConfig {}
    * Setting Active Profiles for Tests
        * org.springframework.test.context.ActiveProfiles
        * org.springframework.context.annotation.Profile
    * Spring Test Properties [HearingInterpreterPropertiesTest](src/test/java/org/springframework/samples/petclinic/sfg/junit5/HearingInterpreterPropertiesTest.java)
        * @Value("${say.word}") -- will be injected by Spring Context
        * @TestPropertySource("classpath:word.properties")  -- annotate test class
    * Assignment - Create Profile for Properties [HearingInterpreterLaurelPropertiesTest](src/test/java/org/springframework/samples/petclinic/sfg/junit5/HearingInterpreterLaurelPropertiesTest.java)
