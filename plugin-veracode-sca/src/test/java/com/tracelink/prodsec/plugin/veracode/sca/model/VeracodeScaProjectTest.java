package com.tracelink.prodsec.plugin.veracode.sca.model;

import org.junit.Assert;
import org.junit.Test;

public class VeracodeScaProjectTest {

	@Test
	public void testGetIssuesForDefaultBranchNoDefaultSet() {
		VeracodeScaProject project = new VeracodeScaProject();
		Assert.assertTrue(project.getIssuesForDefaultBranch().isEmpty());
	}
}
