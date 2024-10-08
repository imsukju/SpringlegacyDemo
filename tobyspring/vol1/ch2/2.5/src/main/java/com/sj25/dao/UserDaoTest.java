//package com.sj25.dao;
//
//
//@ContextConfiguration(locations="/test-applicationContext.xml")
//@DirtiesContext
//public class UserDaoTest {
//	@Autowired
//	ApplicationContext context;
//	
//	private UserDao dao; 
//	
//	private User user1;
//	private User user2;
//	private User user3;
//	
//	@Before
//	public void setUp() {
//		this.dao = this.context.getBean("userDao", UserDao.class);
//		
//		this.user1 = new User("gyumee", "¹Ú¼ºÃ¶", "springno1");
//		this.user2 = new User("leegw700", "ÀÌ±æ¿ø", "springno2");
//		this.user3 = new User("bumjin", "¹Ú¹üÁø", "springno3");
//
//	}
//	
//	@Test 
//	public void andAndGet() throws SQLException {		
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//
//		dao.add(user1);
//		dao.add(user2);
//		assertThat(dao.getCount(), is(2));
//		
//		User userget1 = dao.get(user1.getId());
//		assertThat(userget1.getName(), is(user1.getName()));
//		assertThat(userget1.getPassword(), is(user1.getPassword()));
//		
//		User userget2 = dao.get(user2.getId());
//		assertThat(userget2.getName(), is(user2.getName()));
//		assertThat(userget2.getPassword(), is(user2.getPassword()));
//	}
//
//	@Test(expected=EmptyResultDataAccessException.class)
//	public void getUserFailure() throws SQLException {
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//		
//		dao.get("unknown_id");
//	}
//
//	
//	@Test
//	public void count() throws SQLException {
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//				
//		dao.add(user1);
//		assertThat(dao.getCount(), is(1));
//		
//		dao.add(user2);
//		assertThat(dao.getCount(), is(2));
//		
//		dao.add(user3);
//		assertThat(dao.getCount(), is(3));
//	}
//}
