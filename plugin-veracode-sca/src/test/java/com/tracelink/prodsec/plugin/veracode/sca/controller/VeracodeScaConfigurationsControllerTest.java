package com.tracelink.prodsec.plugin.veracode.sca.controller;

import com.tracelink.prodsec.plugin.veracode.sca.VeracodeScaPlugin;
import com.tracelink.prodsec.plugin.veracode.sca.exception.VeracodeScaClientException;
import com.tracelink.prodsec.plugin.veracode.sca.exception.VeracodeScaProductException;
import com.tracelink.prodsec.plugin.veracode.sca.exception.VeracodeScaThresholdsException;
import com.tracelink.prodsec.plugin.veracode.sca.model.VeracodeScaClient;
import com.tracelink.prodsec.plugin.veracode.sca.model.VeracodeScaThresholds;
import com.tracelink.prodsec.plugin.veracode.sca.service.VeracodeScaClientService;
import com.tracelink.prodsec.plugin.veracode.sca.service.VeracodeScaProjectService;
import com.tracelink.prodsec.plugin.veracode.sca.service.VeracodeScaThresholdsService;
import com.tracelink.prodsec.plugin.veracode.sca.service.VeracodeScaWorkspaceService;
import com.tracelink.prodsec.synapse.auth.SynapseAdminAuthDictionary;
import com.tracelink.prodsec.synapse.mvc.SynapseModelAndView;
import com.tracelink.prodsec.synapse.test.TestSynapseBootApplication;
import java.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestSynapseBootApplication.class)
@AutoConfigureMockMvc
public class VeracodeScaConfigurationsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VeracodeScaClientService clientService;

	@MockBean
	private VeracodeScaThresholdsService thresholdsService;

	@MockBean
	private VeracodeScaWorkspaceService workspaceService;

	@MockBean
	private VeracodeScaProjectService projectService;

	private VeracodeScaClient client;
	private VeracodeScaThresholds thresholds;

	@Before
	public void setup() {
		client = new VeracodeScaClient();
		client.setApiId("abcdef123456");
		client.setApiSecretKey("foo");

		thresholds = new VeracodeScaThresholds();
		thresholds.setGreenYellow(50);
		thresholds.setYellowRed(100);
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testGetConfigurations() throws Exception {
		BDDMockito.when(clientService.getClient()).thenReturn(client);
		BDDMockito.when(thresholdsService.getThresholds()).thenReturn(thresholds);

		mockMvc.perform(MockMvcRequestBuilders.get(VeracodeScaPlugin.CONFIGURATIONS_PAGE))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(
						MockMvcResultMatchers.content()
								.string(Matchers.containsString("abcdef123456")))
				.andExpect(MockMvcResultMatchers.content().string(
						Matchers.stringContainsInOrder(
								Arrays.asList("Green/Yellow", "Yellow/Red", "50", "100"))));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testGetConfigurationsException() throws Exception {
		BDDMockito.when(clientService.getClient()).thenThrow(VeracodeScaClientException.class);
		BDDMockito.when(thresholdsService.getThresholds())
				.thenThrow(VeracodeScaThresholdsException.class);

		mockMvc.perform(MockMvcRequestBuilders.get(VeracodeScaPlugin.CONFIGURATIONS_PAGE))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content()
						.string(Matchers.containsString("No client configured.")))
				.andExpect(
						MockMvcResultMatchers.content()
								.string(Matchers.containsString("No thresholds configured.")));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetApiClientNullEmpty() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/client")
						.param("apiId", "").param("apiSecretKey", "bar")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.FAILURE_FLASH,
								"Please provide all inputs."));

		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/client")
						.param("apiId", "foo").param("apiSecretKey", "")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.FAILURE_FLASH,
								"Please provide all inputs."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetApiClientTrue() throws Exception {
		BDDMockito.when(clientService
				.setClient(BDDMockito.anyString(), BDDMockito.anyString()))
				.thenReturn(true);
		mockMvc
				.perform(MockMvcRequestBuilders
						.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/client")
						.param("apiId", "foo").param("apiSecretKey", "bar")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.SUCCESS_FLASH, "Configured API client."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetApiClientFalse() throws Exception {
		BDDMockito.when(clientService
				.setClient(BDDMockito.anyString(), BDDMockito.anyString()))
				.thenReturn(false);
		mockMvc
				.perform(MockMvcRequestBuilders
						.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/client")
						.param("apiId", "foo")
						.param("apiSecretKey", "bar")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.FAILURE_FLASH, "Invalid API client URL."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testTestConnectionTrue() throws Exception {
		BDDMockito.when(clientService.testConnection()).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/test"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.SUCCESS_FLASH, "Connection successful."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testTestConnectionFalse() throws Exception {
		BDDMockito.when(clientService.testConnection()).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.get(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/test"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.FAILURE_FLASH, "Connection failed."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testFetch() throws Exception {
		mockMvc
				.perform(MockMvcRequestBuilders
						.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/fetch")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.SUCCESS_FLASH,
								"Veracode SCA data fetch in progress."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetThresholdsInvalid() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/thresholds")
						.param("greenYellow", "-10").param("yellowRed", "100")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(
						MockMvcResultMatchers.flash().attribute(SynapseModelAndView.FAILURE_FLASH,
								"Please provide risk thresholds greater than zero, where Green/Yellow is less than Yellow/Red."));

		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/thresholds")
						.param("greenYellow", "60").param("yellowRed", "50")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(
						MockMvcResultMatchers.flash().attribute(SynapseModelAndView.FAILURE_FLASH,
								"Please provide risk thresholds greater than zero, where Green/Yellow is less than Yellow/Red."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetThresholds() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/thresholds")
						.param("greenYellow", "10").param("yellowRed", "100")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.SUCCESS_FLASH,
								"Configured risk score thresholds."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetDefaultBranchProductException() throws Exception {
		BDDMockito.doThrow(VeracodeScaProductException.class).when(projectService)
				.setDefaultBranch("Mock Project", "master");
		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/branch")
						.param("project", "Mock Project").param("defaultBranch", "master")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.FAILURE_FLASH,
								Matchers.containsString(
										"Cannot set default branch for Mock Project.")));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetDefaultBranch() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/branch")
						.param("project", "Mock Project").param("defaultBranch", "master")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.SUCCESS_FLASH,
								"Set default branch for Mock Project."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetIncludedProductException() throws Exception {
		BDDMockito.doThrow(VeracodeScaProductException.class).when(workspaceService)
				.setIncluded("Mock Workspace", true);
		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/include")
						.param("workspace", "Mock Workspace").param("included", "true")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.FAILURE_FLASH,
								Matchers.containsString(
										"Cannot update workspace inclusion status.")));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetIncluded() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/include")
						.param("workspace", "Mock Workspace").param("included", "true")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.SUCCESS_FLASH, "Included workspace."));
	}

	@Test
	@WithMockUser(authorities = {SynapseAdminAuthDictionary.ADMIN_PRIV})
	public void testSetExcluded() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(VeracodeScaPlugin.CONFIGURATIONS_PAGE + "/include")
						.param("workspace", "Mock Workspace").param("included", "false")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.flash()
						.attribute(SynapseModelAndView.SUCCESS_FLASH, "Excluded workspace."));
	}
}
