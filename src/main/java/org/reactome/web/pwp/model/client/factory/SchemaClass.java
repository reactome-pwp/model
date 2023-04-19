package org.reactome.web.pwp.model.client.factory;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 */
public enum SchemaClass {
    AFFILIATION("Affiliation"),
    ABSTRACT_MODIFIED_RESIDUE("AbstractModifiedResidue"),
    ANATOMY("Anatomy"),
    BLACK_BOX_EVENT("BlackBoxEvent"),
    BOOK("Book"),
    CANDIDATE_SET("CandidateSet"),
    CATALYST_ACTIVITY("CatalystActivity"),
    CATALYST_ACTIVITY_REFERENCE("CatalystActivityReference"),
    CELL("Cell"),
    CELL_DEVELOPMENT_STEP("CellDevelopmentStep"),
    CELL_LINEAGE_PATH("CellLineagePath"),
    CELL_TYPE("CellType"),
    CHEMICAL_DRUG("ChemicalDrug"),
    COMPARTMENT("Compartment"),
    COMPLEX("Complex"),
    //    CONTROL_REFERENCE("ControlReference"),
    CONTROLLED_VOCABULARY("ControlledVocabulary"),
    CROSS_LINKED_RESIDUE("CrosslinkedResidue"),
    DATABASE_IDENTIFIER("DatabaseIdentifier"),
    DATABASE_OBJECT("DatabaseObject"),
    DEFINED_SET("DefinedSet"),
    DEPOLYMERISATION("Depolymerisation"),
    DISEASE("Disease"),
    //DRUG("Drug"),
    ENTITY_FUNCTIONAL_STATUS("EntityFunctionalStatus"),
    ENTITY_SET("EntitySet"),
    ENTITY_WITH_ACCESSIONED_SEQUENCE("EntityWithAccessionedSequence", "Protein"),
    EVIDENCE_TYPE("EvidenceType"),
    EXTERNAL_ONTOLOGY("ExternalOntology"),
    FAILED_REACTION("FailedReaction"),
    FIGURE("Figure"),
    FRAGMENT_DELETION_MODIFICATION("FragmentDeletionModification"),
    FRAGMENT_INSERTION_MODIFICATION("FragmentInsertionModification"),
    FRAGMENT_REPLACED_MODIFICATION("FragmentReplacedModification"),
    FRAGMENT_MODIFICATION("FragmentModification"),
    FUNCTIONAL_STATUS("FunctionalStatus"),
    FUNCTIONAL_STATUS_TYPE("FunctionalStatusType"),
    GENETICALLY_MODIFIED_RESIDUE("GeneticallyModifiedResidue"),
    GENOME_ENCODED_ENTITY("GenomeEncodedEntity"),
    GO_BIOLOGICAL_PROCESS("GO_BiologicalProcess"),
    GO_BIOLOGICAL_FUNCTION("GO_MolecularFunction"),
    GO_CELLULAR_COMPONENT("GO_CellularComponent"),
    GO_TERM("GO_Term"),
    GROUP_MODIFIED_RESIDUE("GroupModifiedResidue"),
    INSTANCE_EDIT("InstanceEdit"),
    INTER_CHAIN_CROSSLINKED_RESIDUE("InterChainCrosslinkedResidue"),
    INTRA_CHAIN_CROSSLINKED_RESIDUE("IntraChainCrosslinkedResidue"),
    LITERATURE_REFERENCE("LiteratureReference"),
    MARKER_REFERENCE("MarkerReference"),
    MODIFIED_RESIDUE("ModifiedResidue"),
    MODIFIED_NUCLEOTIDE("ModifiedNucleotide"),
    NEGATIVE_GENE_EXPRESSION_REGULATION("NegativeGeneExpressionRegulation"),
    NEGATIVE_REGULATION("NegativeRegulation"),
    OTHER_ENTITY("OtherEntity"),
    PATHWAY("Pathway"),
    PERSON("Person"),
    PHYSICAL_ENTITY("PhysicalEntity"),
    POLYMER("Polymer"),
    POLYMERISATION("Polymerisation"),
    POSITIVE_GENE_EXPRESSION_REGULATION("PositiveGeneExpressionRegulation"),
    POSITIVE_REGULATION("PositiveRegulation"),
    PROTEIN_DRUG("ProteinDrug"),
    PSI_MOD("PsiMod"),
    PUBLICATION("Publication"),
    REACTION("Reaction"),
    REACTION_LIKE_EVENT("ReactionLikeEvent"),
    REFERENCE_DATABASE("ReferenceDatabase"),
    REFERENCE_DNA_SEQUENCE("ReferenceDNASequence", "Reference DNA sequence"),
    REFERENCE_ENTITY("ReferenceEntity"),
    REFERENCE_GENE_PRODUCT("ReferenceGeneProduct"),
    REFERENCE_GROUP("ReferenceGroup"),
    REFERENCE_ISOFORM("ReferenceIsoform"),
    REFERENCE_MOLECULE("ReferenceMolecule"),
    REFERENCE_RNA_SEQUENCE("ReferenceRNASequence", "Reference RNA sequence"),
    REFERENCE_SEQUENCE("ReferenceSequence"),
    REFERENCE_THERAPEUTIC("ReferenceTherapeutic"),
    REGULATION("Regulation"),
    REGULATION_REFERENCE("RegulationReference"),
    //    REGULATION_TYPE("RegulationType"),
    REPLACED_RESIDUE("ReplacedResidue"),
    NONSENSE_MUTATION("NonsenseMutation"),
    REQUIREMENT("Requirement"),
    RNA_DRUG("RNADrug", "RNA Drug"),
    REVIEW_STATUS("ReviewStatus"),
    SEQUENCE_ONTOLOGY("SequenceOntology"),
    SIMPLE_ENTITY("SimpleEntity"),
    SPECIES("Species"),
    SUMMATION("Summation"),
    TAXON("Taxon"),
    TRANSLATIONAL_MODIFICATION("TranslationalModification"),
    TOP_LEVEL_PATHWAY("TopLevelPathway", "Pathway"),
    URL("URL", "URL");

    public final String schemaClass;
    public final String name;

    SchemaClass(String schemaClass) {
        this(schemaClass, getName(schemaClass));
    }

    SchemaClass(String schemaClass, String name) {
        this.schemaClass = schemaClass;
        this.name = name;
    }

    public static SchemaClass getSchemaClass(String schemaClass) {
        for (SchemaClass sc : values()) {
            if (sc.schemaClass.equals(schemaClass)) return sc;
        }
        return DATABASE_OBJECT;
    }

    private static String getName(String schemaClass) {
        StringBuilder sb = new StringBuilder();
        schemaClass.chars().forEach(c -> {
            if (Character.isUpperCase((char) c))
                sb.append(" ");
            sb.append((char) c);
        });
        return sb.toString().trim();
    }
}
