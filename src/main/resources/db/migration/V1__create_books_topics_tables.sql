create table t_topics(
    id BIGSERIAL PRIMARY KEY,
    created_on TIMESTAMP NOT NULL DEFAULT now(),
    modified_on TIMESTAMP,
    name VARCHAR(32) UNIQUE NOT NULL,
    description TEXT
);

create table t_books(
    id BIGSERIAL PRIMARY KEY,
    created_on TIMESTAMP NOT NULL DEFAULT now(),
    modified_on TIMESTAMP,
    title TEXT NOT NULL,
    author VARCHAR(200) NOT NULL,
    year VARCHAR(4),
    edition VARCHAR(20),
    publisher VARCHAR(20),
    topic_id BIGINT  NOT NULL REFERENCES t_topics(id),
    file_size BIGINT NOT NULL,
    file_extension VARCHAR(10),
    md5 VARCHAR(40) NOT NULL UNIQUE,
    sha1 VARCHAR(42) NOT NULL UNIQUE,
    file_name VARCHAR(40) NOT NULL UNIQUE,
    description TEXT,
    file_path TEXT,
    original_file_name TEXT,
    language VARCHAR(15)
);