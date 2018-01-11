

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import repositories.ActorRepository;


@Component
@Transactional
public class StringToActorConverter implements Converter<String, Actor> {

	@Autowired
	ActorRepository ActorRepository;

	@Override
	public Actor convert(String text) {
		Actor result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = ActorRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
