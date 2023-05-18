package org.reactome.web.pwp.model.client.factory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public interface DatabaseObjectImages extends ClientBundle {

    DatabaseObjectImages INSTANCE = GWT.create(DatabaseObjectImages.class);

    @Source("images/BlackBoxEvent.png")
    ImageResource blackBoxEvent();

    @Source("images/CandidateSet.png")
    ImageResource candidateSet();

    @Source("images/ChemicalDrug.png")
    ImageResource chemicalDrug();

    @Source("images/Cell.png")
    ImageResource Cell();

    @Source("images/Complex.png")
    ImageResource complex();

    @Source("images/ConceptualEvent.png")
    ImageResource conceptualEvent();

    @Source("images/DefinedSet.png")
    ImageResource definedSet();

    @Source("images/Depolymerization.png")
    ImageResource depolymerization();

    @Source("images/EntitySet.png")
    ImageResource entitySet();



    @Source("images/EquivalentEventSet.png")
    ImageResource equivalentEventSet();

    @Source("images/exclamation.png")
    ImageResource exclamation();

    @Source("images/FailedReaction.gif")
    ImageResource failedReaction();

    @Source("images/GenomeEncodeEntity.png")
    ImageResource genomeEncodeEntity();

    @Source("images/isDisease.png")
    ImageResource isDisease();

    @Source("images/isInferred.png")
    ImageResource isInferred();

    @Source("images/NewTag.png")
    ImageResource isNew();

    @Source("images/UpdateTag.png")
    ImageResource isUpdated();

    @Source("images/OpenSet.png")
    ImageResource openSet();

    @Source("images/OtherEntity.png")
    ImageResource otherEntity();

    @Source("images/Pathway.png")
    ImageResource pathway();

    @Source("images/Polymer.png")
    ImageResource polymer();

    @Source("images/Polymerization.png")
    ImageResource polymerization();

    @Source("images/Protein.png")
    ImageResource protein();

    @Source("images/ProteinDrug.png")
    ImageResource proteinDrug();

    @Source("images/Reaction.png")
    ImageResource reaction();

    @Source("images/DNA.png")
    ImageResource referenceDNASequence();

    @Source("images/ReferenceGroup.png")
    ImageResource referenceGroup();

    @Source("images/RNA.png")
    ImageResource RNA();

    @Source("images/RNADrug.png")
    ImageResource RNADrug();

    @Source("images/Regulator.png")
    ImageResource regulator();

    @Source("images/Chemical.png")
    ImageResource simpleEntity();

}
