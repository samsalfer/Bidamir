

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Organiser;
import repositories.OrganiserRepository;


@Component
@Transactional
public class StringToOrganiserConverter implements Converter<String, Organiser> {

	@Autowired
	OrganiserRepository OrganiserRepository;

	@Override
	public Organiser convert(String text) {
		Organiser result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = OrganiserRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
