package sk.nociar.jpacloner.annotationpoints;

import org.junit.Assert;
import org.junit.Test;
import sk.nociar.jpacloner.JpaCloner;
import sk.nociar.jpacloner.PropertyFilters;

import javax.persistence.Id;

/**
 * Simple test for both field and property access.
 */
public class TestBothAccess {

	/*
	 * Commenting testAccessField() out as currently there is no way to implement properly without a JPA
	 * compatible de-proxying mechanism,
	 */
	//	@Test
	//	public void testAccessField() {
	//		final AccessField t1 = new AccessField();
	//		t1.setField(1);
	//
	//		final AccessField t2 = JpaCloner.clone(t1,
	//				PropertyFilters.getAnnotationFilter(Id.class), "*+");
	//
	//		Assert.assertEquals((Integer) 2, t2.getField());
	//	}

	@Test
	public void testAccessProperty() {
		final AccessProperty t1 = new AccessProperty();
		t1.setField(1);

		final AccessProperty t2 = JpaCloner.clone(t1,
				PropertyFilters.getAnnotationFilter(Id.class), "*+");

		Assert.assertEquals((Integer) 4, t2.getField());
	}

	/*
	 * Commenting testAccessField() out as currently there is no way to implement properly without a JPA
	 * compatible de-proxying mechanism,
	 */
	//	@Test
	//	public void testEmbeddableField() {
	//		final Embeddables t1 = new Embeddables();
	//		t1.getField().setField(1);
	//
	//		final Embeddables t2 = JpaCloner.clone(t1,
	//				PropertyFilters.getAnnotationFilter(Id.class), "*+");
	//
	//		Assert.assertEquals((Integer) 2, t2.getField().getField());
	//	}

	@Test
	public void testEmbeddableProperty() {
		final Embeddables t1 = new Embeddables();
		t1.getProperty().setField(1);

		final Embeddables t2 = JpaCloner.clone(t1,
				PropertyFilters.getAnnotationFilter(Id.class), "*+");

		Assert.assertEquals((Integer) 4, t2.getProperty().getField());
	}

	@Test
	public void testFieldAccess() throws NoSuchFieldException {

		final EntityField t1 = new EntityField();
		t1.setId(1);
		t1.setOtherField(2);
		t1.setChild(new EntityFieldChild());
		t1.noSetterField = 3;
		t1.setDifferentNameField2(4);

		final EntityField t2 = JpaCloner.clone(t1,
				PropertyFilters.getAnnotationFilter(Id.class), "*+");

		Assert.assertNull(t2.getId());
		Assert.assertEquals(t2.getOtherField(), (Integer) 2);
		Assert.assertNotNull(t2.getChild());
		Assert.assertNotEquals(t2.getChild(), t1.getChild());
		Assert.assertEquals(t2.getNoSetterField(), t1.getNoSetterField());
		Assert.assertEquals(t2.getDifferentNameField2(), t1.getDifferentNameField2());
	}

	@Test
	public void testPropertyAccess() throws NoSuchFieldException {

		final EntityProperty t1 = new EntityProperty();
		t1.setId(1);
		t1.setOtherField(2);
		t1.setChild(new EntityFieldChild());
		t1.noSetterField = 3;
		t1.setDifferentNameField2(4);

		final EntityProperty t2 = JpaCloner.clone(t1,
				PropertyFilters.getAnnotationFilter(Id.class), "*+");

		Assert.assertNull(t2.getId());
		Assert.assertEquals(t2.getOtherField(), (Integer) 2);
		Assert.assertNotNull(t2.getChild());
		Assert.assertNotEquals(t2.getChild(), t1.getChild());
		Assert.assertNull(t2.getNoSetterField());
		Assert.assertEquals(t2.getDifferentNameField2(), t1.getDifferentNameField2());
	}

	@Test
	public void testNoIdAnnotation() throws NoSuchFieldException {

		final EntityNoId t1 = new EntityNoId();
		t1.setId(1);
		t1.setOtherField(2);
		t1.setDifferentNameField2(4);

		final EntityNoId t2 = JpaCloner.clone(t1,
				PropertyFilters.getAnnotationFilter(Id.class), "*+");

		Assert.assertNotNull(t2.getId());
		Assert.assertEquals(t2.getOtherField(), (Integer) 2);
		Assert.assertEquals(t2.getDifferentNameField2(), t1.getDifferentNameField2());
	}

	@Test
	public void testFieldAccessInheritance() throws NoSuchFieldException {

		final EntityFieldSubClass t1 = new EntityFieldSubClass();
		t1.setId(1);
		t1.setOtherField(2);
		t1.setNewField(3);
		t1.setChild(new EntityFieldChild());
		t1.noSetterField = 3;
		t1.setDifferentNameField2(4);

		final EntityFieldSubClass t2 = JpaCloner.clone(t1,
				PropertyFilters.getAnnotationFilter(Id.class), "*+");

		Assert.assertNull(t2.getId());
		Assert.assertEquals(t2.getOtherField(), (Integer) 2);
		Assert.assertEquals(t2.getNewField(), (Integer) 3);
		Assert.assertNotNull(t2.getChild());
		Assert.assertNotEquals(t2.getChild(), t1.getChild());
		Assert.assertEquals(t2.getNoSetterField(), t1.getNoSetterField());
		Assert.assertEquals(t2.getDifferentNameField2(), t1.getDifferentNameField2());
	}

	@Test
	public void testPropertyAccessInheritance() throws NoSuchFieldException {

		final EntityPropertySubClass t1 = new EntityPropertySubClass();
		t1.setId(1);
		t1.setOtherField(2);
		t1.setNewField(3);
		t1.setChild(new EntityFieldChild());
		t1.noSetterField = 3;
		t1.setDifferentNameField2(4);

		final EntityPropertySubClass t2 = JpaCloner.clone(t1,
				PropertyFilters.getAnnotationFilter(Id.class), "*+");

		Assert.assertNull(t2.getId());
		Assert.assertEquals(t2.getOtherField(), (Integer) 2);
		Assert.assertEquals(t2.getNewField(), (Integer) 3);
		Assert.assertNotNull(t2.getChild());
		Assert.assertNotEquals(t2.getChild(), t1.getChild());
		Assert.assertNull(t2.getNoSetterField());
		Assert.assertEquals(t2.getDifferentNameField2(), t1.getDifferentNameField2());
	}
}
