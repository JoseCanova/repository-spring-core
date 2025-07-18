

import java.util.Optional;

import org.nanotek.beans.entity.BrainzBaseEntity;
import org.nanotek.opencsv.CsvResult;
import org.nanotek.service.jpa.csv.MusicBrainzCsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class CsvProcessorCallBack
<R extends CsvResult<?, B>,
B extends BrainzBaseEntity<B>>
implements ListenableFutureCallback<R> {

	@Autowired
	@Qualifier("MusicBrainzCsvService")
	MusicBrainzCsvService<B,?,?> service;

	private static Logger log = LoggerFactory.getLogger(CsvProcessorCallBack.class);
    private boolean active = true;

	public CsvProcessorCallBack() {
	}

	@Override
	public void onSuccess(R result) {
		Optional.ofNullable(result).ifPresentOrElse(r -> {
			Optional<?> valid = r.on(); // This triggers validation within CsvResult
			valid.ifPresent(v->{
				if(result.isValid())
				{
					// Turing Machine Head Movement: Right (R) - Object is Valid, proceed to persistence
					log.info("TM Head Movement: Right (R) - Object " + r.getId() + " is Valid. Proceeding to persistence.");
					service.verifyBrainzBaseEntity(r.getId());
				} else {
					// Turing Machine Head Movement: Left (L) - Object is Invalid, do not persist
					log.warn("TM Head Movement: Left (L) - Object " + r.getId() + " is Invalid. Skipping persistence.");
				}
			});
		}, new Runnable() {

			@Override
			public void run() {
					active = false;
					log.info("CsvProcessorCallBack: Received null result. Deactivating callback.");
			}

		});

	}

	@Override
	public void onFailure(Throwable ex) {
		log.error("TM Head Movement: Error (Halt) - Exception during processing.",ex); // Log as error
		System.exit(0); // This will halt the application on any failure
	}

	public boolean isActive() {
		return active;
	}

}