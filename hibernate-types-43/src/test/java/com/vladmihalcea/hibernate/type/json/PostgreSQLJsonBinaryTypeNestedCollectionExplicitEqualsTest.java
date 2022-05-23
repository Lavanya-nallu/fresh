package com.vladmihalcea.hibernate.type.json;

import com.vladmihalcea.hibernate.util.AbstractPostgreSQLIntegrationTest;
import com.vladmihalcea.hibernate.util.transaction.JPATransactionFunction;
import net.ttddyy.dsproxy.QueryCount;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.junit.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Vlad Mihalcea
 */
public class PostgreSQLJsonBinaryTypeNestedCollectionExplicitEqualsTest extends AbstractPostgreSQLIntegrationTest {

    @Override
    protected Class<?>[] entities() {
        return new Class<?>[]{
            Post.class,
        };
    }

    private Post _post;

    @Override
    protected void afterInit() {
        QueryCountHolder.clear();

        doInJPA(new JPATransactionFunction<Void>() {

            @Override
            public Void apply(EntityManager entityManager) {
                List<String> attributes = new ArrayList<String>();
                attributes.add("JPA");
                attributes.add("Hibernate");

                PostAttributes customAttributes = new PostAttributes();
                customAttributes.setAttributes(attributes);

                Post post = new Post();
                post.setCustomAttributes(customAttributes);

                entityManager.persist(post);

                _post = post;

                return null;
            }
        });

        QueryCount queryCount = QueryCountHolder.getGrandTotal();
        assertEquals(1, queryCount.getTotal());
        assertEquals(1, queryCount.getInsert());
    }

    @Test
    public void testLoad() {
        QueryCountHolder.clear();

        doInJPA(new JPATransactionFunction<Void>() {

            @Override
            public Void apply(EntityManager entityManager) {
                Post post = entityManager.find(Post.class, _post.getId());
                List<String> attributes = post.getCustomAttributes().getAttributes();

                assertTrue(attributes.contains("JPA"));
                assertTrue(attributes.contains("Hibernate"));

                return null;
            }
        });

        QueryCount queryCount = QueryCountHolder.getGrandTotal();
        assertEquals(1, queryCount.getTotal());
        assertEquals(1, queryCount.getSelect());
        assertEquals(0, queryCount.getUpdate());
    }

    @TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
    @Entity(name = "Post")
    @Table(name = "post")
    public static class Post {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private Long id;

        @Type(type = "jsonb")
        @Column(columnDefinition = "jsonb")
        private PostAttributes customAttributes;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public PostAttributes getCustomAttributes() {
            return customAttributes;
        }

        public void setCustomAttributes(PostAttributes customAttributes) {
            this.customAttributes = customAttributes;
        }
    }

    public static class PostAttributes {

        private List<String> attributes;

        public List<String> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<String> attributes) {
            this.attributes = attributes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PostAttributes)) return false;

            PostAttributes that = (PostAttributes) o;

            return attributes != null ? attributes.equals(that.attributes) : that.attributes == null;
        }

        @Override
        public int hashCode() {
            return attributes != null ? attributes.hashCode() : 0;
        }
    }

}
