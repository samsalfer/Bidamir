

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Essay;
import repositories.EssayRepository;


@Component
@Transactional
public class StringToEssayConverter implements Converter<String, Essay> {

	@Autowired
	EssayRepository EssayRepository;

	@Override
	public Essay convert(String text) {
		Essay result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = EssayRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
