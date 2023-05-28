INSERT INTO users (id, username, email, password) VALUES ('5e8c23b1-5bc8-4d56-8519-09808f3c7bba', 'eagle', 'eagle@example.com', '$2a$10$5bHPac/dHLcFUlazPYlQ9e2G.LqaVwOTgPhhwQeYwfblIBj1Z4XSK');

INSERT INTO role (name)
VALUES ('ROLE_USER');

INSERT INTO user_role (user_id, role_id)
VALUES ('5e8c23b1-5bc8-4d56-8519-09808f3c7bba', 1);


INSERT INTO Augment (name, augment_Class, average_place)
VALUES
    ('Gadgeteen_heart', 0, '4.02'),
    ('Gadgeteen_crest', 1, '4.04'),
    ('Gadgeteen_soul', 2, '4.09'),
    ('Temper_Tantrum', 3, '4.15'),
    ('Celestial_Blessing', 1, '4.35'),
    ('Ascension', 1, '4.45'),
    ('Threat_Level_Maximum', 1, '4.6'),
    ('Infiniteam_heart', 0, '3.91'),
    ('Infiniteam_crest', 1, '4.02'),
    ('Admin_heart', 0, '4.75'),
    ('Ox_force_heart', 0, '4.59'),
    ('Ox_force_crest', 1, '4.28'),
    ('Duelist_heart', 0, '4.3'),
    ('Duelist_crest', 1, '4.41'),
    ('Exiles', 1, '4.32'),
    ('Trade_sector', 1, '4.3'),
    ('Golden_Ticket', 2, '4.5'),
    ('Sureshot_heart', 0, '4.40'),
    ('Thrill_I', 0, '4.59'),
    ('Thrill_II', 1, '4.57');

INSERT INTO Augment_Location(child, parent)
VALUES
    ('Gadgeteen_heart', 'Gadgeteen_crest'),
    ('Gadgeteen_crest', 'Gadgeteen_soul'),
    ('Trade_sector', 'Gadgeteen_soul'),
    ('Celestial_Blessing', 'Gadgeteen_soul'),
    ('Gadgeteen_soul', 'Temper_tantrum'),
    ('Infiniteams_heart', 'Infiniteams_crest'),
    ('Thrill_I', 'Infiniteams_crest'),
    ('Infiniteams_crest', 'Infiniteams_crown'),
    ('Thrill_II', 'Infiniteams_crown'),
    ('Infiniteams_crown', 'Temporal_disruption'),
    ('Duelist_heart', 'Duelist_crest'),
    ('Duelist_crest', 'Duelist_soul'),
    ('Duelist_soul', 'Spreadshot'),
    ('Thrill_II', 'Duelist_soul'),
    ('Sureshot_heart', 'Threat_Level_Maximum'),
    ('Threat_Level_Maximum', 'Electrocharge_III'),
    ('Electrocharge_III', 'Extinction_Event'),
    ('Extinction_Event', 'Game'),
    ('Spreadshot', 'Game'),
    ('Temper_tantrum', 'Game'),
    ('Temporal_disruption', 'Game');



INSERT INTO Component (name, component_type)
VALUES
    ('Recurve_bow',0),
    ('Chain_vest',1),
    ('Tear_of_the_Goddess',0),
    ('Giants_belt',1),
    ('B_F_Sword',0),
    ('Negatrons_cloak',1);

INSERT INTO Item (name)
VALUES
    ('ZZ_rot'),
    ('Titans_resolve'),
    ('Gargoyle_Stoneplate'),
    ('Warmogs'),
    ('Rageblade'),
    ('Statiks_shiv'),
    ('Dragons_claw');

INSERT INTO Champion (name, cost)
VALUES
    ('Lulu', 1),
    ('Poppy', 1),
    ('Annie', 2),
    ('Alistar', 3),
    ('Gnar', 3),
    ('Morgana', 3),
    ('Sona', 3),
    ('Nunu', 5),
    ('Jinx', 2),
    ('Rell', 2),
    ('Lucian', 1),
    ('Sivir', 2),
    ('Ezreal', 2),
    ('Shen', 3),
    ('Garen', 4),
    ('Twisted_Fate', 4),
    ('Fiddlesticks', 5),
    ('Ultimate_Ezreal', 5),
    ('Ashe', 1),
    ('Blitzcrank', 1),
    ('Renekton', 1),
    ('Yasuo', 2),
    ('Riven', 3),
    ('Warwick', 4),
    ('Mordekaiser', 5),
    ('Vi', 2),
    ('Vayne', 3),
    ('Fiora', 1),
    ('Ekko', 4),
    ('Neeko', 4),
    ('Viego', 4),
    ('Lux', 1),
    ('Leona', 5),
    ('Kaisa', 2),
    ('Nilah', 1),
    ('Miss_fortune', 5),
    ('Kayle', 1),
    ('Lee_Sin', 2);


INSERT INTO Composition (name, off_carry, def_carry)
VALUES
    ('Gadgeteens', 'Gnar', 'Annie'),
    ('Infiniteams', 'Ultimate_Ezreal', 'Garen'),
    ('Lasercorps_brawlers', 'Warwick', 'Renekton'),
    ('Ox_force_spellslingers', 'Twisted_Fate', 'Annie'),
    ('Aegies_quickdraw', 'Kaisa', 'Nilah');


INSERT INTO Composition_Augment (composition_name, augment_name, connection)
VALUES
    ('Ox_force_spellslingers', 'Gadgeteen_heart', 0),
    ('Ox_force_spellslingers', 'Gadgeteen_crest', 0),
    ('Ox_force_spellslingers', 'Gadgeteen_soul', 0),
    ('Ox_force_spellslingers', 'Temper_Tantrum', 0),
    ('Ox_force_spellslingers', 'Celestial_Blessing', 85),
    ('Ox_force_spellslingers', 'Ascension', 50),
    ('Ox_force_spellslingers', 'Threat_Level_Maximum', 0),
    ('Ox_force_spellslingers', 'Infiniteam_heart', 0),
    ('Ox_force_spellslingers', 'Infiniteam_crest', 25),
    ('Ox_force_spellslingers', 'Admin_heart', 25),
    ('Ox_force_spellslingers', 'Ox_force_heart', 100),
    ('Ox_force_spellslingers', 'Ox_force_crest', 0),
    ('Ox_force_spellslingers', 'Duelist_heart', 0),
    ('Ox_force_spellslingers', 'Duelist_crest', 0),
    ('Ox_force_spellslingers', 'Exiles', 50),
    ('Ox_force_spellslingers', 'Trade_sector', 65),
    ('Ox_force_spellslingers', 'Golden_Ticket', 15),
    ('Ox_force_spellslingers', 'Sureshot_heart', 40),
    ('Ox_force_spellslingers', 'Thrill_I', 70),
    ('Ox_force_spellslingers', 'Thrill_II', 100),
    ('Aegies_quickdraw', 'Gadgeteen_heart', 0),
    ('Aegies_quickdraw', 'Gadgeteen_crest', 0),
    ('Aegies_quickdraw', 'Gadgeteen_soul', 0),
    ('Aegies_quickdraw', 'Temper_Tantrum', 0),
    ('Aegies_quickdraw', 'Celestial_Blessing', 90),
    ('Aegies_quickdraw', 'Ascension', 45),
    ('Aegies_quickdraw', 'Threat_Level_Maximum', 30),
    ('Aegies_quickdraw', 'Infiniteam_heart', 0),
    ('Aegies_quickdraw', 'Infiniteam_crest', 25),
    ('Aegies_quickdraw', 'Admin_heart', 25),
    ('Aegies_quickdraw', 'Ox_force_heart', 0),
    ('Aegies_quickdraw', 'Ox_force_crest', 0),
    ('Aegies_quickdraw', 'Duelist_heart', 0),
    ('Aegies_quickdraw', 'Duelist_crest', 0),
    ('Aegies_quickdraw', 'Exiles', 50),
    ('Aegies_quickdraw', 'Trade_sector', 55),
    ('Aegies_quickdraw', 'Golden_Ticket', 40),
    ('Aegies_quickdraw', 'Sureshot_heart', 40),
    ('Aegies_quickdraw', 'Thrill_I', 100),
    ('Aegies_quickdraw', 'Thrill_II', 100),
    ('Lasercorps_brawlers', 'Gadgeteen_heart', 0),
    ('Lasercorps_brawlers', 'Gadgeteen_crest', 0),
    ('Lasercorps_brawlers', 'Gadgeteen_soul', 0),
    ('Lasercorps_brawlers', 'Temper_Tantrum', 0),
    ('Lasercorps_brawlers', 'Celestial_Blessing', 50),
    ('Lasercorps_brawlers', 'Ascension', 100),
    ('Lasercorps_brawlers', 'Threat_Level_Maximum', 20),
    ('Lasercorps_brawlers', 'Infiniteam_heart', 0),
    ('Lasercorps_brawlers', 'Infiniteam_crest', 0),
    ('Lasercorps_brawlers', 'Admin_heart', 50),
    ('Lasercorps_brawlers', 'Ox_force_heart', 0),
    ('Lasercorps_brawlers', 'Ox_force_crest', 0),
    ('Lasercorps_brawlers', 'Duelist_heart', 10),
    ('Lasercorps_brawlers', 'Duelist_crest', 40),
    ('Lasercorps_brawlers', 'Exiles', 50),
    ('Lasercorps_brawlers', 'Trade_sector', 90),
    ('Lasercorps_brawlers', 'Golden_Ticket', 20),
    ('Lasercorps_brawlers', 'Sureshot_heart', 0),
    ('Lasercorps_brawlers', 'Thrill_I', 40),
    ('Lasercorps_brawlers', 'Thrill_II', 10),
    ('Infiniteams', 'Gadgeteen_heart', 0),
    ('Infiniteams', 'Gadgeteen_crest', 0),
    ('Infiniteams', 'Gadgeteen_soul', 0),
    ('Infiniteams', 'Temper_Tantrum', 30),
    ('Infiniteams', 'Celestial_Blessing', 40),
    ('Infiniteams', 'Ascension', 40),
    ('Infiniteams', 'Threat_Level_Maximum', 0),
    ('Infiniteams', 'Infiniteam_heart', 100),
    ('Infiniteams', 'Infiniteam_crest', 100),
    ('Infiniteams', 'Admin_heart', 10),
    ('Infiniteams', 'Ox_force_heart', 0),
    ('Infiniteams', 'Ox_force_crest', 0),
    ('Infiniteams', 'Duelist_heart', 20),
    ('Infiniteams', 'Duelist_crest', 70),
    ('Infiniteams', 'Exiles', 50),
    ('Infiniteams', 'Trade_sector', 40),
    ('Infiniteams', 'Golden_Ticket', 70),
    ('Infiniteams', 'Sureshot_heart', 0),
    ('Infiniteams', 'Thrill_I', 30),
    ('Infiniteams', 'Thrill_II', 20),
    ('Gadgeteens', 'Gadgeteen_heart', 100),
    ('Gadgeteens', 'Gadgeteen_crest', 100),
    ('Gadgeteens', 'Gadgeteen_soul', 100),
    ('Gadgeteens', 'Temper_Tantrum', 100),
    ('Gadgeteens', 'Celestial_Blessing', 10),
    ('Gadgeteens', 'Ascension', 20),
    ('Gadgeteens', 'Threat_Level_Maximum', 0),
    ('Gadgeteens', 'Infiniteam_heart', 0),
    ('Gadgeteens', 'Infiniteam_crest', 0),
    ('Gadgeteens', 'Admin_heart', 0),
    ('Gadgeteens', 'Ox_force_heart', 0),
    ('Gadgeteens', 'Ox_force_crest', 0),
    ('Gadgeteens', 'Duelist_heart', 0),
    ('Gadgeteens', 'Duelist_crest', 0),
    ('Gadgeteens', 'Exiles', 50),
    ('Gadgeteens', 'Trade_sector', 90),
    ('Gadgeteens', 'Golden_Ticket', 100),
    ('Gadgeteens', 'Sureshot_heart', 0),
    ('Gadgeteens', 'Thrill_I', 50),
    ('Gadgeteens', 'Thrill_II', 50);


INSERT INTO Composition_Component (composition_name, component_name, connection)
VALUES
    ('Gadgeteens', 'Recurve_bow', 0),
    ('Infiniteams', 'Recurve_bow', 40),
    ('Lasercorps_brawlers', 'Recurve_bow', 60),
    ('Ox_force_spellslingers', 'Recurve_bow', 100),
    ('Aegies_quickdraw', 'Recurve_bow', 100),
    ('Gadgeteens', 'Chain_vest', 100),
    ('Infiniteams', 'Chain_vest', 45),
    ('Lasercorps_brawlers', 'Chain_vest', 20),
    ('Ox_force_spellslingers', 'Chain_vest', 50),
    ('Aegies_quickdraw', 'Chain_vest', 100),
    ('Gadgeteens', 'Tear_of_the_Goddess', 0),
    ('Infiniteams', 'Tear_of_the_Goddess', 10),
    ('Lasercorps_brawlers', 'Tear_of_the_Goddess', 25),
    ('Ox_force_spellslingers', 'Tear_of_the_Goddess', 30),
    ('Aegies_quickdraw', 'Tear_of_the_Goddess', 0),
    ('Gadgeteens', 'Giants_belt', 100),
    ('Infiniteams', 'Giants_belt', 30),
    ('Lasercorps_brawlers', 'Giants_belt', 60),
    ('Ox_force_spellslingers', 'Giants_belt', 0),
    ('Aegies_quickdraw', 'Giants_belt', 80),
    ('Gadgeteens', 'B_F_Sword', 80),
    ('Infiniteams', 'B_F_Sword', 40),
    ('Lasercorps_brawlers', 'B_F_Sword', 50),
    ('Ox_force_spellslingers', 'B_F_Sword', 85),
    ('Aegies_quickdraw', 'B_F_Sword', 90),
    ('Gadgeteens', 'Negatrons_cloak', 70),
    ('Infiniteams', 'Negatrons_cloak', 80),
    ('Lasercorps_brawlers', 'Negatrons_cloak', 0),
    ('Ox_force_spellslingers', 'Negatrons_cloak', 25),
    ('Aegies_quickdraw', 'Negatrons_cloak', 0);

INSERT INTO Champion_Component (champion_name, component_name, connection)
VALUES
    ('Gnar', 'Recurve_bow', 0),
    ('Ultimate_Ezreal', 'Recurve_bow', 40),
    ('Warwick', 'Recurve_bow', 60),
    ('Twisted_Fate', 'Recurve_bow', 100),
    ('Kaisa', 'Recurve_bow', 100),
    ('Annie', 'Recurve_bow', 0),
    ('Garen', 'Recurve_bow', 0),
    ('Renekton', 'Recurve_bow', 0),
    ('Nilah', 'Recurve_bow', 60),
    ('Gnar', 'Chain_vest', 100),
    ('Ultimate_Ezreal', 'Chain_vest', 45),
    ('Warwick', 'Chain_vest', 20),
    ('Twisted_Fate', 'Chain_vest', 50),
    ('Kaisa', 'Chain_vest', 100),
    ('Annie', 'Chain_vest', 100),
    ('Garen', 'Chain_vest', 100),
    ('Renekton', 'Chain_vest', 100),
    ('Nilah', 'Chain_vest', 60),
    ('Gnar', 'Tear_of_the_Goddess', 0),
    ('Ultimate_Ezreal', 'Tear_of_the_Goddess', 10),
    ('Warwick', 'Tear_of_the_Goddess', 25),
    ('Twisted_Fate', 'Tear_of_the_Goddess', 30),
    ('Kaisa', 'Tear_of_the_Goddess', 0),
    ('Annie', 'Tear_of_the_Goddess', 35),
    ('Garen', 'Tear_of_the_Goddess', 50),
    ('Renekton', 'Tear_of_the_Goddess', 30),
    ('Nilah', 'Tear_of_the_Goddess', 70),
    ('Gnar', 'Giants_belt', 100),
    ('Ultimate_Ezreal', 'Giants_belt', 30),
    ('Warwick', 'Giants_belt', 60),
    ('Twisted_Fate', 'Giants_belt', 0),
    ('Kaisa', 'Giants_belt', 30),
    ('Annie', 'Giants_belt', 100),
    ('Garen', 'Giants_belt', 100),
    ('Renekton', 'Giants_belt', 100),
    ('Nilah', 'Giants_belt', 100),
    ('Gnar', 'B_F_Sword', 80),
    ('Ultimate_Ezreal', 'B_F_Sword', 40),
    ('Warwick', 'B_F_Sword', 50),
    ('Twisted_Fate', 'B_F_Sword', 85),
    ('Kaisa', 'B_F_Sword', 90),
    ('Annie', 'B_F_Sword', 60),
    ('Garen', 'B_F_Sword', 70),
    ('Renekton', 'B_F_Sword', 0),
    ('Nilah', 'B_F_Sword', 0),
    ('Gnar', 'Negatrons_cloak', 70),
    ('Ultimate_Ezreal', 'Negatrons_cloak', 80),
    ('Warwick', 'Negatrons_cloak', 0),
    ('Twisted_Fate', 'Negatrons_cloak', 25),
    ('Kaisa', 'Negatrons_cloak', 0),
    ('Annie', 'Negatrons_cloak', 100),
    ('Garen', 'Negatrons_cloak', 100),
    ('Renekton', 'Negatrons_cloak', 40),
    ('Nilah', 'Negatrons_cloak', 100);


INSERT INTO Composition_Item (composition_name, item_name, connection)
VALUES
    ('Gadgeteens', 'ZZ_rot', 90),
    ('Infiniteams', 'ZZ_rot', 60),
    ('Lasercorps_brawlers', 'ZZ_rot', 30),
    ('Ox_force_spellslingers', 'ZZ_rot', 20),
    ('Aegies_quickdraw', 'ZZ_rot', 0),
    ('Gadgeteens', 'Titans_resolve', 100),
    ('Infiniteams', 'Titans_resolve', 0),
    ('Lasercorps_brawlers', 'Titans_resolve', 0),
    ('Ox_force_spellslingers', 'Titans_resolve', 80),
    ('Aegies_quickdraw', 'Titans_resolve', 50),
    ('Gadgeteens', 'Gargoyle_Stoneplate', 100),
    ('Infiniteams', 'Gargoyle_Stoneplate', 50),
    ('Lasercorps_brawlers', 'Gargoyle_Stoneplate', 50),
    ('Ox_force_spellslingers', 'Gargoyle_Stoneplate', 20),
    ('Aegies_quickdraw', 'Gargoyle_Stoneplate', 90),
    ('Gadgeteens', 'Warmogs', 100),
    ('Infiniteams', 'Warmogs', 80),
    ('Lasercorps_brawlers', 'Warmogs', 80),
    ('Ox_force_spellslingers', 'Warmogs', 90),
    ('Aegies_quickdraw', 'Warmogs', 100),
    ('Gadgeteens', 'Rageblade', 0),
    ('Infiniteams', 'Rageblade', 40),
    ('Lasercorps_brawlers', 'Rageblade', 80),
    ('Ox_force_spellslingers', 'Rageblade', 95),
    ('Aegies_quickdraw', 'Rageblade', 100),
    ('Gadgeteens', 'Statiks_shiv', 0),
    ('Infiniteams', 'Statiks_shiv', 40),
    ('Lasercorps_brawlers', 'Statiks_shiv', 40),
    ('Ox_force_spellslingers', 'Statiks_shiv', 90),
    ('Aegies_quickdraw', 'Statiks_shiv', 100),
    ('Gadgeteens', 'Dragons_claw', 50),
    ('Infiniteams', 'Dragons_claw', 30),
    ('Lasercorps_brawlers', 'Dragons_claw', 100),
    ('Ox_force_spellslingers', 'Dragons_claw', 40),
    ('Aegies_quickdraw', 'Dragons_claw', 10);

INSERT INTO Composition_Champion (composition_name, champion_name, connection)
VALUES
    ('Gadgeteens', 'Lulu', 100),
    ('Gadgeteens', 'Poppy', 100),
    ('Gadgeteens', 'Annie', 100),
    ('Gadgeteens', 'Alistar', 100),
    ('Gadgeteens', 'Gnar', 100),
    ('Gadgeteens', 'Morgana', 100),
    ('Gadgeteens', 'Sona', 100),
    ('Gadgeteens', 'Nunu', 100),
    ('Gadgeteens', 'Jinx', 50),
    ('Infiniteams', 'Lucian', 100),
    ('Infiniteams', 'Sivir', 100),
    ('Infiniteams', 'Ezreal', 100),
    ('Infiniteams', 'Shen', 100),
    ('Infiniteams', 'Garen', 100),
    ('Infiniteams', 'Twisted_Fate', 100),
    ('Infiniteams', 'Fiddlesticks', 100),
    ('Infiniteams', 'Ultimate_Ezreal', 100),
    ('Infiniteams', 'Rell', 50),
    ('Lasercorps_brawlers', 'Ashe', 100),
    ('Lasercorps_brawlers', 'Blitzcrank', 100),
    ('Lasercorps_brawlers', 'Renekton', 100),
    ('Lasercorps_brawlers', 'Yasuo', 100),
    ('Lasercorps_brawlers', 'Riven', 100),
    ('Lasercorps_brawlers', 'Warwick', 100),
    ('Lasercorps_brawlers', 'Garen', 100),
    ('Lasercorps_brawlers', 'Mordekaiser', 100),
    ('Lasercorps_brawlers', 'Vi', 50),
    ('Lasercorps_brawlers', 'Vayne', 50),
    ('Ox_force_spellslingers', 'Annie', 100),
    ('Ox_force_spellslingers', 'Fiora', 100),
    ('Ox_force_spellslingers', 'Alistar', 100),
    ('Ox_force_spellslingers', 'Sona', 100),
    ('Ox_force_spellslingers', 'Ekko', 100),
    ('Ox_force_spellslingers', 'Neeko', 100),
    ('Ox_force_spellslingers', 'Twisted_Fate', 100),
    ('Ox_force_spellslingers', 'Viego', 100),
    ('Ox_force_spellslingers', 'Lux', 50),
    ('Ox_force_spellslingers', 'Leona', 50),
    ('Aegies_quickdraw', 'Rell', 100),
    ('Aegies_quickdraw', 'Kaisa', 100),
    ('Aegies_quickdraw', 'Nilah', 100),
    ('Aegies_quickdraw', 'Ekko', 100),
    ('Aegies_quickdraw', 'Garen', 100),
    ('Aegies_quickdraw', 'Miss_fortune', 100),
    ('Aegies_quickdraw', 'Neeko', 100),
    ('Aegies_quickdraw', 'Kayle', 50),
    ('Aegies_quickdraw', 'Ezreal', 50),
    ('Aegies_quickdraw', 'Lee_Sin', 50),
    ('Aegies_quickdraw', 'Vi', 50);


INSERT INTO Champion_Location (child, parent)
VALUES
    ('Lulu', 'Annie'),
    ('Poppy', 'Annie'),
    ('Annie', 'Alistar'),
    ('Annie', 'Gnar'),
    ('Annie', 'Sona'),
    ('Annie', 'Morgana'),
    ('Alistar', 'Gadgeteens'),
    ('Gnar', 'Gadgeteens'),
    ('Sona', 'Gadgeteens'),
    ('Morgana', 'Gadgeteens'),
    ('Lucian', 'Sivir'),
    ('Lucian', 'Ezreal'),
    ('Ezreal', 'Shen'),
    ('Shen', 'Garen'),
    ('Shen', 'Twisted_fate'),
    ('Twisted_fate', 'Fiddlesticks'),
    ('Twisted_fate', 'Ultimate_Ezreal'),
    ('Ultimate_Ezreal', 'Infiniteams'),
    ('Fiddlesticks', 'Ultimate_Ezreal'),
    ('Ashe', 'Yasuo'),
    ('Blitzcrank', 'Yasuo'),
    ('Renekton', 'Yasuo'),
    ('Yasuo', 'Riven'),
    ('Riven', 'Warwick'),
    ('Riven', 'Garen'),
    ('Garen', 'Mordekaiser'),
    ('Mordekaiser', 'Lasercorps_brawlers'),
    ('Fiora', 'Alistar'),
    ('Fora', 'Sona'),
    ('Alistar', 'Ekko'),
    ('Sona', 'Ekko'),
    ('Alistar', 'Twisted_Fate'),
    ('Sona', 'Twisted_Fate'),
    ('Alistar', 'Viego'),
    ('Sona', 'Viego'),
    ('Ekko', 'Ox_force_spellslingers'),
    ('Twisted_fate', 'Ox_force_spellslingers'),
    ('Viego', 'Ox_force_spellslingers'),
    ('Rell', 'Kaisa'),
    ('Rell', 'Nilah'),
    ('Kaisa', 'Ekko'),
    ('Nilah', 'Ekko'),
    ('Kaisa', 'Garen'),
    ('Nilah', 'Garen'),
    ('Kaisa', 'Miss_fortune'),
    ('Nilah', 'Miss_fortune'),
    ('Kaisa', 'Neeko'),
    ('Nilah', 'Neeko'),
    ('Neeko', 'Aegies_quickdraw'),
    ('Miss_fortune', 'Aegies_quickdraw'),
    ('Garen', 'Aegies_quickdraw'),
    ('Ekko', 'Aegies_quickdraw');


insert into Game(id, level, round, hp, gold, place)
values
    (1, 3, 0, 100, 0, 0)