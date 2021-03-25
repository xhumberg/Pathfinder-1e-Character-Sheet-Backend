package com.xavier.basicPathfinderServer.databaseLayer;

import java.util.List;

import com.xavier.basicPathfinderServer.Adjustment;
import com.xavier.basicPathfinderServer.CharacterClass;
import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.Spell;
import com.xavier.basicPathfinderServer.ResultSetMappers.AllowedAdjustmentsMapper;
import com.xavier.basicPathfinderServer.ResultSetMappers.ClassMapper;
import com.xavier.basicPathfinderServer.ResultSetMappers.EnabledAdjustmentsMapper;
import com.xavier.basicPathfinderServer.ResultSetMappers.PathfinderCharacterMapper;
import com.xavier.basicPathfinderServer.ResultSetMappers.PreppedSpellMapper;

public class CharacterFromDatabaseLoader {

	private final static String GET_CHARACTER_QUERY = "SELECT * FROM PathfinderCharacter WHERE CharacterID = ?";
	private final static String GET_ALLOWED_ADJUSTMENTS_QUERY = "select AdjustmentName, AdjustmentEffect from AllowedAdjustments inner join StandardAdjustments on AllowedAdjustments.AdjustmentID = StandardAdjustments.AdjustmentID where CharacterID = ?";
	private final static String GET_ENABLED_ADJUSTMENTS_QUERY = "select AdjustmentName from EnabledAdjustments inner join StandardAdjustments on EnabledAdjustments.AdjustmentID = StandardAdjustments.AdjustmentID where CharacterID = ?";
	private final static String GET_CLASSES_FOR_CHARACTER = "SELECT * FROM Classes INNER JOIN CharacterClasses ON Classes.ClassID = CharacterClasses.ClassID WHERE CharacterClasses.CharacterID = ?";
	private final static String GET_PREPPED_SPELLS_QUERY = "select *, SpellsPrepped.SpellLevel AS levelPreppedAt, Spells.SpellLevel AS levelInformation from Spells inner join SpellsPrepped on SpellsPrepped.SpellID = Spells.SpellID where SpellsPrepped.CharacterID = ?";
	@SuppressWarnings("unchecked")
	public static PathfinderCharacter loadCharacter(String idString) {
		int id = Integer.parseInt(idString);
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		PathfinderCharacter character = (PathfinderCharacter)db.executeSelectQuery(new PathfinderCharacterMapper(), GET_CHARACTER_QUERY, id);
		
		if (character != null) {
			List<Adjustment> allowedAdjustments = (List<Adjustment>)db.executeSelectQuery(new AllowedAdjustmentsMapper(), GET_ALLOWED_ADJUSTMENTS_QUERY, id);
			character.setAllowedAdjustments(allowedAdjustments);
			List<String> enabledAdjustments = (List<String>)db.executeSelectQuery(new EnabledAdjustmentsMapper(), GET_ENABLED_ADJUSTMENTS_QUERY, id);
			character.toggleAdjustments(enabledAdjustments);
			List<CharacterClass> classes = (List<CharacterClass>)db.executeSelectQuery(new ClassMapper(), GET_CLASSES_FOR_CHARACTER, id);
			List<Spell> PreppedSpells = (List<Spell>)db.executeSelectQuery(new PreppedSpellMapper(), GET_PREPPED_SPELLS_QUERY, id);
			db.close();
			return character;
		}
		db.close();
		return new PathfinderCharacter("Error: Couldn't load character", "https://www.aautomate.com/images/easyblog_shared/November_2018/11-12-18/human_error_stop_400.png");
	}
}
