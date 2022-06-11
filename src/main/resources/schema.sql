DROP TABLE IF EXISTS rate;

CREATE TABLE exercise (
    id UUID PRIMARY KEY NOT NULL,
    source VARCHAR(3) NOT NULL,
    code_list_code VARCHAR(10) NOT NULL,
    code VARCHAR(255) UNIQUE NOT NULL,
    display_value VARCHAR(255) NOT NULL,
    long_description TEXT,
    from_date TIMESTAMP,
    to_date TIMESTAMP,
    sorting_priority INT
);
