package org.jasig.ssp.model.reference;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * JournalTrack reference object.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class JournalTrack extends AbstractReference implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private int sortOrder;

	@OneToMany(mappedBy = "journalTrack")
	private Set<JournalTrackJournalStep> journalTrackJournalSteps = new HashSet<JournalTrackJournalStep>(
			0);

	/**
	 * Constructor
	 */
	public JournalTrack() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Identifier; required
	 */

	public JournalTrack(UUID id) {
		super(id);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Identifier; required
	 * @param name
	 *            Name; required; max 100 characters
	 */

	public JournalTrack(UUID id, String name) {
		super(id, name);
	}

	/**
	 * Constructor
	 * 
	 * @param id
	 *            Identifier; required
	 * @param name
	 *            Name; required; max 100 characters
	 * @param description
	 *            Description; max 150 characters
	 */
	public JournalTrack(UUID id, String name, String description) {
		super(id, name, description);
	}

	@Override
	protected int hashPrime() {
		return 227;
	}

	@Override
	final public int hashCode() {
		int result = hashPrime();

		// Auditable properties
		result *= getId() == null ? "id".hashCode() : getId().hashCode();
		result *= getObjectStatus() == null ? hashPrime() : getObjectStatus()
				.hashCode();

		result *= sortOrder > 0 ? sortOrder : hashPrime();

		return result;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Set<JournalTrackJournalStep> getJournalTrackJournalSteps() {
		return journalTrackJournalSteps;
	}

	public void setJournalTrackJournalSteps(
			Set<JournalTrackJournalStep> journalTrackJournalSteps) {
		this.journalTrackJournalSteps = journalTrackJournalSteps;
	}
}
