package com.revature.coi.revanauts.tests.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.calls;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.revature.coi.revanauts.models.Skill;
import com.revature.coi.revanauts.repos.SkillRepository;
import com.revature.coi.revanauts.services.SkillService;
import com.revature.coi.revanauts.services.SkillServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SkillServiceTest {

	@Mock
	private SkillRepository mockSkillRepo;
	
	@InjectMocks
	private SkillService sut = new SkillServiceImpl(mockSkillRepo);
	
	private Skill mockValidSkill;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockValidSkill = new Skill(1L, "test skill", "test skill", "test skill");
	}
	
	@Test
	public void getAllSkillsWhenSkillsAreFoundTest() {
		List<Skill> expectedResult = new ArrayList<>();
		expectedResult.add(mockValidSkill);
		when(mockSkillRepo.findAll()).thenReturn(expectedResult);
		List<Skill> actualResult = sut.getAllSkills();
		verify(mockSkillRepo, times(1)).findAll();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void getAllSkillsWhenSkillsAreNotFoundTest() {
		when(mockSkillRepo.findAll()).thenReturn(new ArrayList<>());
		sut.getAllSkills();
		verify(mockSkillRepo, times(1)).findAll();
	}
	
	@Test
	public void getSkillByValidAndKnownId() {
		Skill expectedResult = new Skill(1L, "test skill", "test skill", "test skill");
		when(mockSkillRepo.findById(1L)).thenReturn(Optional.of(expectedResult));
		Skill actualResult = sut.getSkillById(1L);
		verify(mockSkillRepo, times(1)).findById(1L);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void getSkillByValidAndUnknownId() {
		when(mockSkillRepo.findById(10000L)).thenReturn(Optional.empty());
		sut.getSkillById(10000L);
		verify(mockSkillRepo, calls(1)).findById(10000L);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void getSkillByInvalidId() {
		sut.getSkillById(-1L);
		verify(mockSkillRepo, never()).findById(Mockito.anyLong());
	}
	
	@Test
	public void getSkillByValidAndKnownName() {
		Skill expectedResult = new Skill(1L, "test skill", "test skill", "test skill");
		when(mockSkillRepo.findSkillByName(Mockito.anyString())).thenReturn(expectedResult);
		Skill actualResult = sut.getSkillByName("test skill");
		verify(mockSkillRepo, times(1)).findSkillByName("test skill");
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void getSkillByValidAndUnknownName() {
		when(mockSkillRepo.findSkillByName(Mockito.anyString())).thenReturn(null);
		sut.getSkillByName("fake skill");
		verify(mockSkillRepo, times(1)).findSkillByName("fake skill");
	}
	
	@Test(expected = ResponseStatusException.class)
	public void getSkillByNullName() {
		when(mockSkillRepo.findById(-1L)).thenReturn(Optional.empty());
		sut.getSkillById(-1L);
		verify(mockSkillRepo, never()).findSkillByName(Mockito.anyString());
	}
	
	@Test
	public void addNewValidSkillNotDuplicateTest() {
		Skill expectedResult = mockValidSkill;
		Skill newValidSkill = new Skill(0L, "test skill", "test skill", "test skill");
		when(mockSkillRepo.findSkillByName(Mockito.anyString())).thenReturn(null);
		when(mockSkillRepo.save(newValidSkill)).thenReturn(expectedResult);
		Skill actualResult = sut.addNewSkill(newValidSkill);
		verify(mockSkillRepo, times(1)).save(Mockito.any());
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void addNewValidSkillDuplicateNameTest() {
		Skill duplicateValidSkill = new Skill(0L, "test skill", "test skill", "test skill");
		when(mockSkillRepo.findSkillByName(Mockito.anyString())).thenReturn(mockValidSkill);
		sut.addNewSkill(duplicateValidSkill);
		verify(mockSkillRepo, times(1)).save(Mockito.any());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void addNewInvalidSkillWithNullNameTest() {
		Skill invalidSkill = new Skill(0L, null, "test skill", "test skill");
		sut.addNewSkill(invalidSkill);
		verify(mockSkillRepo, never()).save(Mockito.any());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void addNewInvalidSkillWithNullDescriptionTest() {
		Skill invalidSkill = new Skill(0L, "test skill", null, "test skill");
		sut.addNewSkill(invalidSkill);
		verify(mockSkillRepo, never()).save(Mockito.any());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void addNewInvalidSkillWithNullTipsTest() {
		Skill invalidSkill = new Skill(0L, "test skill", "test skill", null);
		sut.addNewSkill(invalidSkill);
		verify(mockSkillRepo, never()).save(Mockito.any());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void addNewNullSkillTest() {
		sut.addNewSkill(null);
		verify(mockSkillRepo, times(1)).save(Mockito.any());
	}
	
	@Test
	public void updateExistingSkillWithValidSkillNotDuplicateTest() {
		Skill expectedResult = new Skill(1L, "updated skill", "test skill", "test skill");
		Skill updatedValidSkill = new Skill(1L, "updated skill", "test skill", "test skill");
		when(mockSkillRepo.findById(1L)).thenReturn(Optional.of(mockValidSkill));
		when(mockSkillRepo.findSkillByName(Mockito.anyString())).thenReturn(null);
		when(mockSkillRepo.save(updatedValidSkill)).thenReturn(expectedResult);
		Skill actualResult = sut.addNewSkill(updatedValidSkill);
		verify(mockSkillRepo, times(1)).save(Mockito.any());
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void updateExistingValidSkillWithDuplicateNameTest() {
		Skill updatedValidSkillDuplicateName = new Skill(1L, "test skill", "test skill", "test skill");
		when(mockSkillRepo.findById(1L)).thenReturn(Optional.of(mockValidSkill));
		when(mockSkillRepo.findSkillByName(Mockito.anyString())).thenReturn(mockValidSkill);
		sut.addNewSkill(updatedValidSkillDuplicateName);
		verify(mockSkillRepo, times(1)).save(Mockito.any());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void updateSkillWithInvalidSkillId() {
		Skill invalidSkill = new Skill(0L, "test skill", "test skill", "test skill");
		sut.updateSkill(invalidSkill);
		verify(mockSkillRepo, never()).save(Mockito.any());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void updateExistingSkillWithNullNameTest() {
		Skill invalidSkill = new Skill(1L, null, "test skill", "test skill");
		sut.updateSkill(invalidSkill);
		verify(mockSkillRepo, never()).save(Mockito.any());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void updateExistingSkillWithNullDescriptionTest() {
		Skill invalidSkill = new Skill(0L, "test skill", null, "test skill");
		sut.updateSkill(invalidSkill);
		verify(mockSkillRepo, never()).save(Mockito.any());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void updateExistingSkillWithNullTipsTest() {
		Skill invalidSkill = new Skill(0L, "test skill", "test skill", null);
		sut.updateSkill(invalidSkill);
		verify(mockSkillRepo, never()).save(Mockito.any());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void updateSkillWithNullSkillTest() {
		sut.updateSkill(null);
		verify(mockSkillRepo, never()).save(Mockito.any());
	}
	
	@Test
	public void deleteSkillWithValidId() {
		sut.deleteSkillById(1L);
		verify(mockSkillRepo, times(1)).deleteById(Mockito.anyLong());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void deleteSkillWithInvalidId() {
		sut.deleteSkillById(-1L);
		verify(mockSkillRepo, never()).deleteById(Mockito.anyLong());
	}
	
}
