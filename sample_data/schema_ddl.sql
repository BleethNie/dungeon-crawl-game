DROP TABLE IF EXISTS public.game_state CASCADE;
CREATE TABLE public.game_state (
                                   id serial NOT NULL PRIMARY KEY,
                                   save_name text NOT NULL,
                                   current_map text NOT NULL,
                                   saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.items;
CREATE TABLE public.items (

                              id integer NOT NULL ,
                              item_name text NOT NULL ,
                              message text NOT NULL,
                              x integer NOT NULL ,
                              y integer NOT NULL ,
                              points integer NOT NULL,
                              inventory boolean NOT NULL,
                              game_state_id integer NOT NULL,
                              map_number integer NOT NULL

);


DROP TABLE IF EXISTS public.monsters;
CREATE TABLE public.monsters (
                                 id integer NOT NULL ,
                                 monster_name text NOT NULL ,
                                 hp integer NOT NULL,
                                 x integer NOT NULL,
                                 y integer NOT NULL ,
                                 game_state_id integer NOT NULL,
                                 map_number integer NOT NULL
);

DROP TABLE IF EXISTS  public.player CASCADE ;
CREATE TABLE public.player (
                               id serial NOT NULL PRIMARY KEY,
                               player_name text NOT NULL,
                               hp integer NOT NULL,
                               armor integer NOT NULL ,
                               strength integer not null ,
                               x integer NOT NULL,
                               y integer NOT NULL,
                               game_state_id integer NOT NULL
);

ALTER TABLE ONLY public.player
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.items
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.monsters
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id);
