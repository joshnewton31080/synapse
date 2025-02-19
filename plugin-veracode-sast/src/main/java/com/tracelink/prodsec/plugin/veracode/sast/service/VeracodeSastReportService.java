package com.tracelink.prodsec.plugin.veracode.sast.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracelink.prodsec.plugin.veracode.sast.model.VeracodeSastReportModel;
import com.tracelink.prodsec.plugin.veracode.sast.repository.VeracodeSastReportRepository;

/**
 * Handles business logic for saving and retrieving reports
 * 
 * @author csmith
 *
 */
@Service
public class VeracodeSastReportService {

	private final VeracodeSastReportRepository reportRepo;

	public VeracodeSastReportService(@Autowired VeracodeSastReportRepository reportRepo) {
		this.reportRepo = reportRepo;
	}

	// Report Methods
	public VeracodeSastReportModel save(VeracodeSastReportModel report) {
		return reportRepo.saveAndFlush(report);
	}

	public Optional<VeracodeSastReportModel> getReportById(long reportId) {
		return reportRepo.findById(reportId);
	}

	public VeracodeSastReportModel getReportForAnalysisAndBuild(long analysisId, long buildId) {
		return reportRepo.findByAnalysisIdAndBuildId(analysisId, buildId);
	}
}
