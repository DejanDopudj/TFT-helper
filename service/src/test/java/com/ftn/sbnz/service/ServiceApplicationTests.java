package com.ftn.sbnz.service;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceApplicationTests {

	@Test
	void hello() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.newKieClasspathContainer();
		KieSession ksession = kc.newKieSession("cepKsession");
		long ruleFireCount = ksession.fireAllRules();
		System.out.println(ruleFireCount);
	}
/*
	public void addAllCombinations() {
		addComposition("Gadgeteens", "Gadgeteen_heart", 100);
		addComposition("Gadgeteens", "Gadgeteen_crest", 100);
		addComposition("Gadgeteens", "Gadgeteen_soul", 100);
		addComposition("Gadgeteens", "Temper_Tantrum", 100);
		addComposition("Gadgeteens", "Celestial_Blessing", 10);
		addComposition("Gadgeteens", "Ascension", 20);
		addComposition("Gadgeteens", "Threat_Level_Maximum", 0);
		addComposition("Gadgeteens", "Infiniteam_heart", 0);
		addComposition("Gadgeteens", "Infiniteam_crest", 0);
		addComposition("Gadgeteens", "Admin_heart", 0);
		addComposition("Gadgeteens", "Ox_force_heart", 0);
		addComposition("Gadgeteens", "Ox_force_crest", 0);
		addComposition("Gadgeteens", "Duelist_heart", 0);
		addComposition("Gadgeteens", "Duelist_crest", 0);
		addComposition("Gadgeteens", "Exiles", 50);
		addComposition("Gadgeteens", "Trade_sector", 90);
		addComposition("Gadgeteens", "Golden_Ticket", 100);
		addComposition("Gadgeteens", "Sureshot_heart", 0);
		addComposition("Gadgeteens", "Thrill_I", 50);
		addComposition("Gadgeteens", "Thrill_II", 50);

		addComposition("Infiniteams", "Gadgeteen_heart", 0);
		addComposition("Infiniteams", "Gadgeteen_crest", 0);
		addComposition("Infiniteams", "Gadgeteen_soul", 0);
		addComposition("Infiniteams", "Temper_Tantrum", 30);
		addComposition("Infiniteams", "Celestial_Blessing", 40);
		addComposition("Infiniteams", "Ascension", 40);
		addComposition("Infiniteams", "Threat_Level_Maximum", 0);
		addComposition("Infiniteams", "Infiniteam_heart", 100);
		addComposition("Infiniteams", "Infiniteam_crest", 100);
		addComposition("Infiniteams", "Admin_heart", 10);
		addComposition("Infiniteams", "Ox_force_heart", 0);
		addComposition("Infiniteams", "Ox_force_crest", 0);
		addComposition("Infiniteams", "Duelist_heart", 20);
		addComposition("Infiniteams", "Duelist_crest", 70);
		addComposition("Infiniteams", "Exiles", 50);
		addComposition("Infiniteams", "Trade_sector", 40);
		addComposition("Infiniteams", "Golden_Ticket", 70);
		addComposition("Infiniteams", "Sureshot_heart", 0);
		addComposition("Infiniteams", "Thrill_I", 30);
		addComposition("Infiniteams", "Thrill_II", 20);

		addComposition("Lasercorps_brawlers", "Gadgeteen_heart", 0);
		addComposition("Lasercorps_brawlers", "Gadgeteen_crest", 0);
		addComposition("Lasercorps_brawlers", "Gadgeteen_soul", 0);
		addComposition("Lasercorps_brawlers", "Temper_Tantrum", 0);
		addComposition("Lasercorps_brawlers", "Celestial_Blessing", 50);
		addComposition("Lasercorps_brawlers", "Ascension", 50);
		addComposition("Lasercorps_brawlers", "Threat_Level_Maximum", 20);
		addComposition("Lasercorps_brawlers", "Infiniteam_heart", 0);
		addComposition("Lasercorps_brawlers", "Infiniteam_crest", 0);
		addComposition("Lasercorps_brawlers", "Admin_heart", 50);
		addComposition("Lasercorps_brawlers", "Ox_force_heart", 0);
		addComposition("Lasercorps_brawlers", "Ox_force_crest", 0);
		addComposition("Lasercorps_brawlers", "Duelist_heart", 10);
		addComposition("Lasercorps_brawlers", "Duelist_crest", 40);
		addComposition("Lasercorps_brawlers", "Exiles", 50);
		addComposition("Lasercorps_brawlers", "Trade_sector", 90);
		addComposition("Lasercorps_brawlers", "Golden_Ticket", 20);
		addComposition("Lasercorps_brawlers", "Sureshot_heart", 0);
		addComposition("Lasercorps_brawlers", "Thrill_I", 40);
		addComposition("Lasercorps_brawlers", "Thrill_II", 10);

		addComposition("Ox_force_spellslingers", "Gadgeteen_heart", 0);
		addComposition("Ox_force_spellslingers", "Gadgeteen_crest", 0);
		addComposition("Ox_force_spellslingers", "Gadgeteen_soul", 0);
		addComposition("Ox_force_spellslingers", "Temper_Tantrum", 0);
		addComposition("Ox_force_spellslingers", "Celestial_Blessing", 85);
		addComposition("Ox_force_spellslingers", "Ascension", 50);
		addComposition("Ox_force_spellslingers", "Threat_Level_Maximum", 0);
		addComposition("Ox_force_spellslingers", "Infiniteam_heart", 0);
		addComposition("Ox_force_spellslingers", "Infiniteam_crest", 25);
		addComposition("Ox_force_spellslingers", "Admin_heart", 25);
		addComposition("Ox_force_spellslingers", "Ox_force_heart", 100);
		addComposition("Ox_force_spellslingers", "Ox_force_crest", 0);
		addComposition("Ox_force_spellslingers", "Duelist_heart", 0);
		addComposition("Ox_force_spellslingers", "Duelist_crest", 0);
		addComposition("Ox_force_spellslingers", "Exiles", 50);
		addComposition("Ox_force_spellslingers", "Trade_sector", 65);
		addComposition("Ox_force_spellslingers", "Golden_Ticket", 15);
		addComposition("Ox_force_spellslingers", "Sureshot_heart", 40);
		addComposition("Ox_force_spellslingers", "Thrill_I", 70);
		addComposition("Ox_force_spellslingers", "Thrill_II", 100);

		addComposition("Aegies_quickdraw", "Gadgeteen_heart", 0);
		addComposition("Aegies_quickdraw", "Gadgeteen_crest", 0);
		addComposition("Aegies_quickdraw", "Gadgeteen_soul", 0);
		addComposition("Aegies_quickdraw", "Temper_Tantrum", 0);
		addComposition("Aegies_quickdraw", "Celestial_Blessing", 90);
		addComposition("Aegies_quickdraw", "Ascension", 45);
		addComposition("Aegies_quickdraw", "Threat_Level_Maximum", 30);
		addComposition("Aegies_quickdraw", "Infiniteam_heart", 0);
		addComposition("Aegies_quickdraw", "Infiniteam_crest", 25);
		addComposition("Aegies_quickdraw", "Admin_heart", 25);
		addComposition("Aegies_quickdraw", "Ox_force_heart", 0);
		addComposition("Aegies_quickdraw", "Ox_force_crest", 0);
		addComposition("Aegies_quickdraw", "Duelist_heart", 0);
		addComposition("Aegies_quickdraw", "Duelist_crest", 0);
		addComposition("Aegies_quickdraw", "Exiles", 50);
		addComposition("Aegies_quickdraw", "Trade_sector", 55);
		addComposition("Aegies_quickdraw", "Golden_Ticket", 40);
		addComposition("Aegies_quickdraw", "Sureshot_heart", 40);
		addComposition("Aegies_quickdraw", "Thrill_I", 100);
		addComposition("Aegies_quickdraw", "Thrill_II", 100);
	}
*/
}