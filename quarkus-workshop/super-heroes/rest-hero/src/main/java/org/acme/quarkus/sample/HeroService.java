package org.acme.quarkus.sample;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.Valid;

@ApplicationScoped
@Transactional(value = TxType.REQUIRED)
public class HeroService {

	@Transactional(value = TxType.SUPPORTS)
	public List<Hero> findAllHeroes() {
		return Hero.listAll();
	}

	@Transactional(value = TxType.SUPPORTS)
	public Hero findHeroById(Long id) {
		return Hero.findById(id);
	}

	@Transactional(value = TxType.SUPPORTS)
	public Hero findRandomHero() {
		Hero randomHero = null;
		while (randomHero == null) {
			randomHero = Hero.findRandom();
		}
		return randomHero;
	}

	public void deleteHero(Long id) {
		Hero hero = Hero.findById(id);
		hero.delete();
	}

	public Hero persistHero(@Valid Hero hero) {
		Hero.persist(hero);
		return hero;
	}

	public Hero updateHero(@Valid Hero hero) {
		Hero entity = Hero.findById(hero.id);
		entity.name = hero.name;
		entity.otherName = hero.otherName;
		entity.level = hero.level;
		entity.picture = hero.picture;
		entity.powers = hero.powers;
		return entity;
	}

}
