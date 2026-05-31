-- Insert test API Keys
INSERT INTO api_key (id, key_hash, company, active, created_at) VALUES ('aaa00000-0000-0000-0000-000000000001', '93e08d75d40523d42bac5777cd4bab24147f0d12287fffc22bf4cbad4b7034be', 'SpaceX', true, CURRENT_TIMESTAMP());
INSERT INTO api_key (id, key_hash, company, active, created_at) VALUES ('aaa00000-0000-0000-0000-000000000002', 'd34d4715554805a55ba4b0f69e6d749ccf6f243ef9f4e35d0db1b951ebaeb8ed', 'ESA', true, CURRENT_TIMESTAMP());

-- Insert test satellites
INSERT INTO satellite (id, name, owner_company, norad_id, orbit_type, altitude_km, inclination, status, created_at, updated_at) VALUES ('bbb00000-0000-0000-0000-000000000001', 'StarLink-1234', 'SpaceX', '48274', 'LEO', 550.0, 53.0, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO satellite (id, name, owner_company, norad_id, orbit_type, altitude_km, inclination, status, created_at, updated_at) VALUES ('bbb00000-0000-0000-0000-000000000002', 'Sentinel-2A',   'ESA',    '40697', 'LEO', 786.0, 98.6, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO satellite (id, name, owner_company, norad_id, orbit_type, altitude_km, inclination, status, created_at, updated_at) VALUES ('bbb00000-0000-0000-0000-000000000003', 'Intelsat-35e',  'Intelsat','42818','GEO', 35786.0, 0.1, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insert a sample collision event
INSERT INTO collision_event (id, satellite_id, object_name, probability, closest_approach, distance_km, severity, resolved, created_at) VALUES ('ccc00000-0000-0000-0000-000000000001', 'bbb00000-0000-0000-0000-000000000001', 'Debris-2019-006', 0.032, DATEADD('DAY', 7, CURRENT_TIMESTAMP()), 0.8, 'HIGH', false, CURRENT_TIMESTAMP());

-- Insert sample subscriptions
INSERT INTO subscription (id, satellite_id, api_key_id, created_at) VALUES ('ddd00000-0000-0000-0000-000000000001', 'bbb00000-0000-0000-0000-000000000001', 'aaa00000-0000-0000-0000-000000000001', CURRENT_TIMESTAMP());

